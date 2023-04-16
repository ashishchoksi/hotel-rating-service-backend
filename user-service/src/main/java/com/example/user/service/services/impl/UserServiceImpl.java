package com.example.user.service.services.impl;

import com.example.user.service.client.HotelServiceClient;
import com.example.user.service.client.RatingServiceClient;
import com.example.user.service.entities.Rating;
import com.example.user.service.entities.User;
import com.example.user.service.exceptions.ResourceNotFoundException;
import com.example.user.service.repositories.UserRepository;
import com.example.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final HotelServiceClient hotelServiceClient;
    private final RatingServiceClient ratingServiceClient;

    @Override
    public User save(User user) {
        String userId = UUID.randomUUID().toString(); // Generating unique id
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found with id: " + userId));
        List<Rating> userRating = ratingServiceClient.getUserRatings(userId);
        userRating.forEach(rating -> {
            rating.setHotel(hotelServiceClient.getHotelDetail(rating.getHotelId()));
        });
        user.setRatings(userRating);
        return user;
    }

}
