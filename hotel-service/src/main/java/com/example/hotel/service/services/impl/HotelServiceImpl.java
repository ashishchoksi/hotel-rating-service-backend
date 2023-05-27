package com.example.hotel.service.services.impl;

import com.example.hotel.service.entities.HotelCassandra;
import com.example.hotel.service.exceptions.ResourceNotFoundException;
import com.example.hotel.service.repositories.HotelCassandraRepository;
import com.example.hotel.service.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    // private final HotelRepository hotelRepository;
    private final HotelCassandraRepository hotelRepository;

    @Override
    public HotelCassandra save(HotelCassandra hotel) {
         String hotelId = UUID.randomUUID().toString();
         hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<HotelCassandra> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public HotelCassandra getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("No hotel found for id: " + hotelId));
    }
}
