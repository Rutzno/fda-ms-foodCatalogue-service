package com.diarpy.foodcatalogueservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mack_TB
 * @since 29/09/2024
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDTO {
    private Integer id;
    private String itemName;
    private String itemDescription;
    private boolean isVeg;
    private Integer price;
    private Integer quantity;
    private Integer restaurantId;
}
