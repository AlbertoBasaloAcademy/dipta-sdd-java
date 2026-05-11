package academy.aicode.astrobookings.service;

import academy.aicode.astrobookings.model.Launch;
import academy.aicode.astrobookings.model.LaunchStatus;
import academy.aicode.astrobookings.repository.LaunchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LaunchService {
    private final LaunchRepository repository;

    public LaunchService(LaunchRepository repository) {
        this.repository = repository;
    }

    public List<Launch> getAllLaunches() {
        return repository.findAll();
    }

    public Optional<Launch> getLaunchById(UUID id) {
        return repository.findById(id);
    }

    public Launch createLaunch(Launch launch) {
        launch.setStatus(LaunchStatus.created);
        return repository.save(launch);
    }

    public Launch updateLaunch(UUID id, Launch launchData) {
        Launch launch = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Launch not found"));

        if (launch.getStatus() == LaunchStatus.cancelled) {
            throw new IllegalStateException("Cannot update a cancelled launch");
        }

        launch.setRocketId(launchData.getRocketId());
        launch.setLaunchTime(launchData.getLaunchTime());
        launch.setPricePerTicket(launchData.getPricePerTicket());
        launch.setMinimumOccupancy(launchData.getMinimumOccupancy());
        launch.setStatus(launchData.getStatus());

        return repository.save(launch);
    }
}
