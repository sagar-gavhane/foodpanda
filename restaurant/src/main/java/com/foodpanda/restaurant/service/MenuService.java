package com.foodpanda.restaurant.service;

import com.foodpanda.restaurant.dto.MenuDto;
import com.foodpanda.restaurant.entity.Menu;
import com.foodpanda.restaurant.entity.Restaurant;
import com.foodpanda.restaurant.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ModelMapper modelMapper;

    public List<MenuDto> getMenus(Integer restaurantId, List<Integer> menuIds) {
        // 1. if restaurant id present and menu ids are present then fetch only those menus
        // 2. if restaurant id present and menu ids are not present then fetch all menus of restaurant
        // 3. if restaurant id is not present then fetch all menus
        if (restaurantId != null && menuIds != null && !menuIds.isEmpty()) {
            List<Menu> menus = menuRepository.findAllById(menuIds);
            return menus.stream()
                    .map(menu -> modelMapper.map(menu, MenuDto.class))
                    .collect(Collectors.toList());
        }

        if (restaurantId != null) {
            Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
            List<Menu> menus = menuRepository.findAllByRestaurant(restaurant);

            return menus.stream()
                    .map(menu -> modelMapper.map(menu, MenuDto.class))
                    .collect(Collectors.toList());
        }

        if (menuIds != null && !menuIds.isEmpty()) {
            List<Menu> menus = menuRepository.findAllById(menuIds);

            return menus.stream()
                    .map(menu -> modelMapper.map(menu, MenuDto.class))
                    .collect(Collectors.toList());
        }

        List<MenuDto> menuDtos = menuRepository.findAll().stream()
                .map(menu -> modelMapper.map(menu, MenuDto.class))
                .collect(Collectors.toList());

        return menuDtos;
    }

    public MenuDto addMenu(Integer restaurantId, Menu menu) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        menu.setRestaurant(restaurant);
        Menu savedMenu = menuRepository.save(menu);

        MenuDto menuDto = modelMapper.map(savedMenu, MenuDto.class);
        return menuDto;
    }
}
