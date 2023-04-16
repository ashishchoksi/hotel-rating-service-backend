package com.example.user.service.client;

import com.example.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE", path = "/ratings")
public interface RatingServiceClient {

    @GetMapping("/users/{userId}")
    List<Rating> getUserRatings(@PathVariable("userId") String userId);

}
