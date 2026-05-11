package academy.aicode.astrobookings.service;

import academy.aicode.astrobookings.model.Launch;
import academy.aicode.astrobookings.model.LaunchStatus;
import academy.aicode.astrobookings.repository.LaunchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LaunchServiceTest {

    private LaunchRepository repository;
    private LaunchService service;

    @BeforeEach
    void setUp() {
        repository = mock(LaunchRepository.class);
        service = new LaunchService(repository);
    }

    @Test
    void shouldSetStatusToCreatedOnLaunchCreation() {
        Launch launch = new Launch();
        launch.setRocketId(UUID.randomUUID());
        launch.setLaunchTime(LocalDateTime.now().plusDays(10));
        launch.setPricePerTicket(100.0);
        launch.setMinimumOccupancy(5);
        launch.setStatus(null);

        when(repository.save(any(Launch.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Launch created = service.createLaunch(launch);

        assertEquals(LaunchStatus.created, created.getStatus());
        verify(repository).save(launch);
    }

    @Test
    void shouldUpdateLaunchWhenNotCancelled() {
        UUID id = UUID.randomUUID();
        Launch existing = new Launch(id, UUID.randomUUID(), LocalDateTime.now(), 100.0, 5, LaunchStatus.created);
        Launch updateData = new Launch(id, UUID.randomUUID(), LocalDateTime.now().plusDays(1), 200.0, 10, LaunchStatus.confirmed);

        when(repository.findById(id)).thenReturn(Optional.of(existing));
        when(repository.save(any(Launch.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Launch updated = service.updateLaunch(id, updateData);

        assertEquals(LaunchStatus.confirmed, updated.getStatus());
        assertEquals(200.0, updated.getPricePerTicket());
        verify(repository).save(existing);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingCancelledLaunch() {
        UUID id = UUID.randomUUID();
        Launch existing = new Launch(id, UUID.randomUUID(), LocalDateTime.now(), 100.0, 5, LaunchStatus.cancelled);
        Launch updateData = new Launch(id, UUID.randomUUID(), LocalDateTime.now().plusDays(1), 200.0, 10, LaunchStatus.confirmed);

        when(repository.findById(id)).thenReturn(Optional.of(existing));

        assertThrows(IllegalStateException.class, () -> service.updateLaunch(id, updateData));
        verify(repository, never()).save(any());
    }
}
