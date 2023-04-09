package com.example.hotel.service.services;

import com.example.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel save(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotel(String hotelId);

}
