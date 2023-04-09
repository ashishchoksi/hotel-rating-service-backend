package com.example.hotel.service.services.impl;

import com.example.hotel.service.entities.Hotel;
import com.example.hotel.service.exceptions.ResourceNotFoundException;
import com.example.hotel.service.repositories.HotelRepository;
import com.example.hotel.service.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotel save(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("No hotel found for id: " + hotelId));
    }
}
