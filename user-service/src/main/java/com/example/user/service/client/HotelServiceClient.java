package com.example.user.service.client;

import com.example.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * if we only provide service name to the client
 * it will try to look up in eureka service registry and find the host & port
 */
@FeignClient(name = "HOTEL-SERVICE", path = "/hotels")
public interface HotelServiceClient {

    @GetMapping("/{hotelId}")
    Hotel getHotelDetail(@PathVariable("hotelId") String hotelId);

}
