package com.foodpanda.restaurant.repository;

import com.foodpanda.restaurant.entity.Menu;
import com.foodpanda.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findAllByRestaurant(Restaurant restaurant);
}
