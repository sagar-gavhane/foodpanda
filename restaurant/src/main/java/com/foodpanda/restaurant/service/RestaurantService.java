package com.foodpanda.restaurant.service;

import com.foodpanda.restaurant.dto.RestaurantDto;
import com.foodpanda.restaurant.entity.Restaurant;
import com.foodpanda.restaurant.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<RestaurantDto> getRestaurants() {
        return restaurantRepository
                .findAll()
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .collect(Collectors.toList());
    }

    public Restaurant getRestaurantById(Integer restaurantId) {
        return restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
    }
}
