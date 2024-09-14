package com.foodpanda.restaurant.controller;

import com.foodpanda.restaurant.dto.MenuDto;
import com.foodpanda.restaurant.entity.Menu;
import com.foodpanda.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity<List<MenuDto>> getMenus(@RequestParam(name = "restaurantId", required = false) Integer restaurantId, @RequestParam(name = "menuIds", required = false) List<Integer> menuIds) {
        List<MenuDto> menus = menuService.getMenus(restaurantId, menuIds);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuDto> addMenu(@RequestParam("restaurantId") Integer restaurantId, @RequestBody Menu menu) {
        MenuDto addedMenu = menuService.addMenu(restaurantId, menu);
        return new ResponseEntity<>(addedMenu, HttpStatus.CREATED);
    }
}
