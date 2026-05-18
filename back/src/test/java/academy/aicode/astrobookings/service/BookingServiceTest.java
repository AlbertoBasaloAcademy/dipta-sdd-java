package academy.aicode.astrobookings.service;

import academy.aicode.astrobookings.model.*;
import academy.aicode.astrobookings.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    private BookingRepository bookingRepository;
    private LaunchService launchService;
    private RocketService rocketService;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingRepository = mock(BookingRepository.class);
        launchService = mock(LaunchService.class);
        rocketService = mock(RocketService.class);
        bookingService = new BookingService(bookingRepository, launchService, rocketService);
    }

    @Test
    void shouldCreateBookingWhenValid() {
        UUID launchId = UUID.randomUUID();
        UUID rocketId = UUID.randomUUID();
        Launch launch = new Launch(launchId, rocketId, LocalDateTime.now(), 100.0, 5, LaunchStatus.created);
        Rocket rocket = new Rocket(rocketId, "Falcon 9", 2, RocketRange.Earth);
        
        Booking booking = new Booking();
        booking.setLaunchId(launchId);
        booking.setPassengerName("John Doe");
        booking.setPassengerEmail("john@example.com");

        when(launchService.getLaunchById(launchId)).thenReturn(Optional.of(launch));
        when(rocketService.getRocketById(rocketId)).thenReturn(Optional.of(rocket));
        when(bookingRepository.findAll()).thenReturn(Collections.emptyList());
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArgument(0));

        Booking created = bookingService.createBooking(booking);

        assertNotNull(created);
        assertEquals(BookingStatus.created, created.getStatus());
        verify(bookingRepository).save(booking);
    }

    @Test
    void shouldThrowExceptionWhenLaunchFull() {
        UUID launchId = UUID.randomUUID();
        UUID rocketId = UUID.randomUUID();
        Launch launch = new Launch(launchId, rocketId, LocalDateTime.now(), 100.0, 5, LaunchStatus.created);
        Rocket rocket = new Rocket(rocketId, "Falcon 9", 1, RocketRange.Earth);
        
        Booking existingBooking = new Booking(UUID.randomUUID(), launchId, "Existing", "ex@test.com", "123", BookingStatus.created);
        
        Booking newBooking = new Booking();
        newBooking.setLaunchId(launchId);
        newBooking.setPassengerName("John Doe");
        newBooking.setPassengerEmail("john@example.com");

        when(launchService.getLaunchById(launchId)).thenReturn(Optional.of(launch));
        when(rocketService.getRocketById(rocketId)).thenReturn(Optional.of(rocket));
        when(bookingRepository.findAll()).thenReturn(Collections.singletonList(existingBooking));

        assertThrows(IllegalStateException.class, () -> bookingService.createBooking(newBooking));
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailInvalid() {
        Booking booking = new Booking();
        booking.setLaunchId(UUID.randomUUID());
        booking.setPassengerName("John Doe");
        booking.setPassengerEmail("invalid-email");

        assertThrows(IllegalArgumentException.class, () -> bookingService.createBooking(booking));
    }

    @Test
    void shouldCancelBooking() {
        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking(bookingId, UUID.randomUUID(), "John", "john@test.com", "123", BookingStatus.created);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArgument(0));

        Booking cancelled = bookingService.cancelBooking(bookingId);

        assertEquals(BookingStatus.cancelled, cancelled.getStatus());
        verify(bookingRepository).save(booking);
    }
}
