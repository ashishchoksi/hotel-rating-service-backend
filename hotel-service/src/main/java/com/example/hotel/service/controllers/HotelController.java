package com.example.hotel.service.controllers;

import com.example.hotel.service.entities.Hotel;
import com.example.hotel.service.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel newHotel = hotelService.save(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newHotel);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
        Hotel hotel = hotelService.getHotel(hotelId);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

}
