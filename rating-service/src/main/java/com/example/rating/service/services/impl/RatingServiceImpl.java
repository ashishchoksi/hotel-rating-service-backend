package com.example.rating.service.services.impl;

import com.example.rating.service.entities.Rating;
import com.example.rating.service.exceptions.ResourceNotFoundException;
import com.example.rating.service.repositories.RatingRepositoryMongo;
import com.example.rating.service.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    /**
     * We have started with H2 DB repository
     * Now we are using MongoDB repository
     */
    // private final RatingRepository ratingRepository;
    private final RatingRepositoryMongo ratingRepository;

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRating(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("no rating found for id: " + ratingId));
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
