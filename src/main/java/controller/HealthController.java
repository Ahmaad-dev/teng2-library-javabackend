package controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> health = Map.of(
            "status", "UP",
            "service", "Library Management System",
            "timestamp", java.time.Instant.now().toString(),
            "version", "1.0.0"
        );
        return ResponseEntity.ok(health);
    }
}