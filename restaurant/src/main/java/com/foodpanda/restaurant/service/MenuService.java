package com.foodpanda.restaurant.service;

import com.foodpanda.restaurant.entity.Menu;
import com.foodpanda.restaurant.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getMenus(Integer restaurantId) {
        // TODO: impl.
        return menuRepository.findAll();
    }

    public Menu addMenu(Integer restaurantId, Menu menu) {
        // TODO: impl
        return menuRepository.save(menu);
    }
}
