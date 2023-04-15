package com.example.user.service.services.impl;

import com.example.user.service.entities.Hotel;
import com.example.user.service.entities.Rating;
import com.example.user.service.entities.User;
import com.example.user.service.exceptions.ResourceNotFoundException;
import com.example.user.service.repositories.UserRepository;
import com.example.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

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
        List<Rating> userRating = getUserRating(userId);
        userRating.forEach(rating -> {
            rating.setHotel(getHotelDetail(rating));
        });
        user.setRatings(userRating);
        return user;
    }

    private Hotel getHotelDetail(Rating rating) {
        String hotelServiceUrl = String.format("http://localhost:8082/hotels/%s", rating.getHotelId());
        Hotel hotel = restTemplate.getForObject(hotelServiceUrl, Hotel.class);
        return hotel;
    }

    private List<Rating> getUserRating(String userId) {
        String ratingServiceUrl = String.format("http://localhost:8083/ratings/users/%s", userId);
        log.info("making API call to service: {}", ratingServiceUrl);
        Rating[] ratings = restTemplate.getForObject(ratingServiceUrl, Rating[].class);
        return Arrays.stream(ratings).collect(Collectors.toList());
    }
}
