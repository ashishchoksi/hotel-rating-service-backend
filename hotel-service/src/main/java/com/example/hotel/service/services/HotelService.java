package com.example.hotel.service.services;

import com.example.hotel.service.entities.HotelCassandra;
import java.util.List;

public interface HotelService {

    HotelCassandra save(HotelCassandra hotel);

    List<HotelCassandra> getAllHotels();

    HotelCassandra getHotel(String hotelId);

}
