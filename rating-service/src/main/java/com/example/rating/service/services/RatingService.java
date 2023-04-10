package com.example.rating.service.services;

import com.example.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating save(Rating rating);
    Rating getRating(String ratingId);
    List<Rating> getAllRatings();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
