package com.diarpy.foodcatalogueservice.service;

import com.diarpy.foodcatalogueservice.dto.FoodCataloguePage;
import com.diarpy.foodcatalogueservice.dto.FoodItemDTO;
import com.diarpy.foodcatalogueservice.dto.Restaurant;
import com.diarpy.foodcatalogueservice.entity.FoodItem;
import com.diarpy.foodcatalogueservice.mapper.FoodItemMapper;
import com.diarpy.foodcatalogueservice.repository.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Mack_TB
 * @since 29/09/2024
 * @version 1.0.0
 */

@Service
public class FoodCatalogueService {
    private final FoodItemRepo foodItemRepo;
    private final RestTemplate restTemplate;

    @Autowired
    public FoodCatalogueService(FoodItemRepo foodItemRepo, RestTemplate restTemplate) {
        this.foodItemRepo = foodItemRepo;
        this.restTemplate = restTemplate;
    }

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.toFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.toFoodItemDTO(foodItem);
    }

    public FoodCataloguePage findFoodCataloguePage(Integer restaurantId) {
        Restaurant restaurant = findRestaurantByIdFromMS(restaurantId);
        List<FoodItem> foodItems = findByRestaurantId(restaurantId);
        return createFoodCataloguePage(restaurant, foodItems);
    }

    private FoodCataloguePage createFoodCataloguePage(Restaurant restaurant, List<FoodItem> foodItems) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setRestaurant(restaurant);
        foodCataloguePage.setFoodItems(foodItems);
        return foodCataloguePage;
    }

    private Restaurant findRestaurantByIdFromMS(Integer restaurantId) {
        String apiUrl = "http://RESTAURANTLISTING-SERVICE/restaurant/restaurants/" + restaurantId;
        return restTemplate.getForObject(apiUrl, Restaurant.class);
    }

    private List<FoodItem> findByRestaurantId(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
