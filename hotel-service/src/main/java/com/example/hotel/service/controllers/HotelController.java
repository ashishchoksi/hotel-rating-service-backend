package com.example.hotel.service.controllers;

import com.example.hotel.service.entities.HotelCassandra;
import com.example.hotel.service.services.HotelService;
import com.google.common.collect.ImmutableList;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Hotel rating service")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("")
    @Operation(summary = "create the hotel", description = "create the hotel with valid payload")
    public ResponseEntity<HotelCassandra> createHotel(@RequestBody HotelCassandra hotel) {
        HotelCassandra newHotel = hotelService.save(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newHotel);
    }

    @GetMapping("/{hotelId}")
    @Operation(summary = "get the hotel by id", description = "get the single hotel by id")
    public ResponseEntity<HotelCassandra> getHotel(@PathVariable String hotelId) {
        HotelCassandra hotel = hotelService.getHotel(hotelId);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("")
    @Operation(summary = "get all the hotels", description = "get all the hotels with ratelimit")
    @RateLimiter(name = "hotelsRateLimit", fallbackMethod = "hotelLimitExceed")
    public ResponseEntity<List<HotelCassandra>> getAllHotels() {
        List<HotelCassandra> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    public ResponseEntity<List<HotelCassandra>> hotelLimitExceed(Exception ex) {
        log.error("Too many request from user...");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ImmutableList.of());
    }

}
