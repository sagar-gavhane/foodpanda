package com.foodpanda.restaurant.service;

import com.foodpanda.restaurant.entity.Restaurant;
import com.foodpanda.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }
}
