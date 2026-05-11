package academy.aicode.astrobookings.controller;

import academy.aicode.astrobookings.model.Launch;
import academy.aicode.astrobookings.service.LaunchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/launches")
@CrossOrigin(origins = "*")
public class LaunchController {
    private final LaunchService service;

    public LaunchController(LaunchService service) {
        this.service = service;
    }

    @GetMapping
    public List<Launch> getAllLaunches() {
        return service.getAllLaunches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Launch> getLaunchById(@PathVariable UUID id) {
        return service.getLaunchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Launch> createLaunch(@RequestBody Launch launch) {
        Launch created = service.createLaunch(launch);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Launch> updateLaunch(@PathVariable UUID id, @RequestBody Launch launch) {
        try {
            Launch updated = service.updateLaunch(id, launch);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Launch not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }
}
