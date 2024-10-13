package com.diarpy.foodcatalogueservice.controller;

import com.diarpy.foodcatalogueservice.dto.FoodCataloguePage;
import com.diarpy.foodcatalogueservice.dto.FoodItemDTO;
import com.diarpy.foodcatalogueservice.service.FoodCatalogueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mack_TB
 * @since 29/09/2024
 * @version 1.0.0
 */

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {
    private final FoodCatalogueService foodCatalogueService;

    public FoodCatalogueController(FoodCatalogueService foodCatalogueService) {
        this.foodCatalogueService = foodCatalogueService;
    }

    @PostMapping("/foodItems")
    public ResponseEntity<FoodItemDTO> createFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO savedFoodItem = foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(savedFoodItem, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsByRestaurantID/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> getFoodItemsByRestaurant(@PathVariable Integer restaurantId) {
        FoodCataloguePage foodCataloguePage = foodCatalogueService.findFoodCataloguePage(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }
}
