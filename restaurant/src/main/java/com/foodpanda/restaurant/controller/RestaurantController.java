package com.foodpanda.restaurant.controller;

import com.foodpanda.restaurant.dto.RestaurantDto;
import com.foodpanda.restaurant.entity.Restaurant;
import com.foodpanda.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getRestaurants() {
        List<RestaurantDto> restaurants = restaurantService.getRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant addedRestaurant = restaurantService.addRestaurant(restaurant);
        return new ResponseEntity<>(addedRestaurant, HttpStatus.CREATED);
    }
}
