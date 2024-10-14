package com.diarpy.foodcatalogueservice.service;

import com.diarpy.foodcatalogueservice.dto.FoodCataloguePage;
import com.diarpy.foodcatalogueservice.dto.FoodItemDTO;
import com.diarpy.foodcatalogueservice.dto.Restaurant;
import com.diarpy.foodcatalogueservice.entity.FoodItem;
import com.diarpy.foodcatalogueservice.mapper.FoodItemMapper;
import com.diarpy.foodcatalogueservice.repository.FoodItemRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FoodCatalogueServiceTest {
    @InjectMocks
    private FoodCatalogueService foodCatalogueService;
    @Mock
    private FoodItemRepo foodItemRepo;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFoodItem() {
        // Arrange
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        FoodItem foodItem = new FoodItem();
        when(foodItemRepo.save(any(FoodItem.class))).thenReturn(foodItem);

        // Act
        FoodItemDTO result = foodCatalogueService.addFoodItem(foodItemDTO);

        // Assert
        verify(foodItemRepo, times(1)).save(any(FoodItem.class));
        Assertions.assertEquals(FoodItemMapper.INSTANCE.toFoodItemDTO(foodItem), result);
    }

    @Test
    void testFindFoodCataloguePage() {
        // Arrange
        int restaurantId = 123;
        List<FoodItem> foodItemList = Arrays.asList(new FoodItem());
        Restaurant restaurant = new Restaurant();
        when(foodItemRepo.findByRestaurantId(restaurantId)).thenReturn(foodItemList);
        when(restTemplate.getForObject(anyString(), eq(Restaurant.class))).thenReturn(restaurant);

        // Act
        FoodCataloguePage result = foodCatalogueService.findFoodCataloguePage(restaurantId);

        // Assert
        verify(foodItemRepo, times(1)).findByRestaurantId(restaurantId);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Restaurant.class));
        Assertions.assertEquals(foodItemList, result.getFoodItems());
        Assertions.assertEquals(restaurant, result.getRestaurant());
    }
}