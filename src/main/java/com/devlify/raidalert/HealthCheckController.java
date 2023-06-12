package com.devlify.raidalert;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HealthCheckController {

    private final Map<String, String> OK = Collections.singletonMap("status", "success");

    @GetMapping("/api/health")
    public Object health() {
        return OK;
    }

}
