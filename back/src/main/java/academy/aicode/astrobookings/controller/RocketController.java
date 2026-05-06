package academy.aicode.astrobookings.controller;

import academy.aicode.astrobookings.model.Rocket;
import academy.aicode.astrobookings.service.RocketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rockets")
@CrossOrigin(origins = "*")
public class RocketController {
    private final RocketService service;

    public RocketController(RocketService service) {
        this.service = service;
    }

    @GetMapping
    public List<Rocket> getAllRockets() {
        return service.getAllRockets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rocket> getRocketById(@PathVariable UUID id) {
        return service.getRocketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rocket> createRocket(@RequestBody Rocket rocket) {
        try {
            Rocket created = service.createRocket(rocket);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rocket> updateRocket(@PathVariable UUID id, @RequestBody Rocket rocket) {
        try {
            Rocket updated = service.updateRocket(id, rocket);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Rocket not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> decommissionRocket(@PathVariable UUID id) {
        try {
            service.decommissionRocket(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
