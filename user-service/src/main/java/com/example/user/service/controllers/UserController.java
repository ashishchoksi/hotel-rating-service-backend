package com.example.user.service.controllers;

import com.example.user.service.entities.User;
import com.example.user.service.services.UserService;
import com.google.common.collect.ImmutableList;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingFailureFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> ratingFailureFallback(String userId, Exception e) {
        log.error("Failed to get the users with error: ", e);
        return ResponseEntity.ok(User.builder().userId("dummay").email("dummay@gmail.com").build());
    }

    @GetMapping("")
    @Retry(name = "userRatingService", fallbackMethod = "retryLimitExceed")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Getting all the users...");
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<List<User>> retryLimitExceed(Exception ex) {
        log.error("Retry Limit Exceed... ");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ImmutableList.of());
    }
}
