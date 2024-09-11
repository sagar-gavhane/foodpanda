package com.foodpanda.restaurant.controller;

import com.foodpanda.restaurant.entity.Menu;
import com.foodpanda.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity<List<Menu>> getMenus(@PathVariable("restaurantId") Integer restaurantId) {
        List<Menu> menus = menuService.getMenus(restaurantId);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Menu> addMenu(@PathVariable("restaurantId") Integer restaurantId, @RequestBody Menu menu) {
        Menu addedMenu = menuService.addMenu(restaurantId, menu);
        return new ResponseEntity<>(addedMenu, HttpStatus.CREATED);
    }
}
