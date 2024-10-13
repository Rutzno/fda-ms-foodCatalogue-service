package com.diarpy.foodcatalogueservice.dto;

import com.diarpy.foodcatalogueservice.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Mack_TB
 * @since 29/09/2024
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePage {
    private List<FoodItem> foodItems;
    private Restaurant restaurant;
}
