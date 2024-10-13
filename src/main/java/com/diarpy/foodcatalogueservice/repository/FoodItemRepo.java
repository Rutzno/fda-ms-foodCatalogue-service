package com.diarpy.foodcatalogueservice.repository;

import com.diarpy.foodcatalogueservice.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mack_TB
 * @since 29/09/2024
 * @version 1.0.0
 */

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Integer> {
    List<FoodItem> findByRestaurantId(Integer restaurantId);
}
