package com.diarpy.foodcatalogueservice.controller;

import com.diarpy.foodcatalogueservice.dto.FoodCataloguePage;
import com.diarpy.foodcatalogueservice.dto.FoodItemDTO;
import com.diarpy.foodcatalogueservice.service.FoodCatalogueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Mack_TB
 * @since 14/10/2024
 * @version 1.0.0
 */

class FoodCatalogueControllerTest {
    @InjectMocks
    private FoodCatalogueController foodCatalogueController;
    @Mock
    private FoodCatalogueService foodCatalogueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFoodItem() {
        // Arrange
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        when(foodCatalogueService.addFoodItem(any(FoodItemDTO.class))).thenReturn(foodItemDTO);

        // Act
        ResponseEntity<FoodItemDTO> response = foodCatalogueController.createFoodItem(foodItemDTO);

        // Assert
        verify(foodCatalogueService, times(1)).addFoodItem(foodItemDTO);
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() == foodItemDTO;
    }

    @Test
    void testGetFoodItemsByRestaurant() {
        // Arrange
        int restaurantId = 123;
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        when(foodCatalogueService.findFoodCataloguePage(restaurantId)).thenReturn(foodCataloguePage);

        // Act
        ResponseEntity<FoodCataloguePage> response = foodCatalogueController.getFoodItemsByRestaurant(restaurantId);

        // Assert
        verify(foodCatalogueService, times(1)).findFoodCataloguePage(restaurantId);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == foodCataloguePage;
    }
}