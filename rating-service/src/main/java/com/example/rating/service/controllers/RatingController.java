package com.example.rating.service.controllers;

import com.example.rating.service.entities.Rating;
import com.example.rating.service.services.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
@Tag(name = "Rating service")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("")
    @Operation(summary = "create ratings", description = "create the ratings")
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.save(rating));
    }

    @GetMapping("/{ratingId}")
    @Operation(summary = "get rating by id", description = "get single rating by id")
    public ResponseEntity<Rating> getRating(@PathVariable String ratingId) {
        return ResponseEntity.ok(ratingService.getRating(ratingId));
    }

    @GetMapping("")
    @Operation(summary = "get all ratings", description = "get all ratings")
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("users/{userId}")
    @Operation(summary = "get all ratings for given user", description = "get all ratings for user")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("hotels/{hotelId}")
    @Operation(summary = "get all ratings for given hotel", description = "get all the ratings for given hotel")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
}
