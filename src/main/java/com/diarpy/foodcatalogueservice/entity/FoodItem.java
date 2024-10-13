package com.diarpy.foodcatalogueservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mack_TB
 * @since 29/09/2024
 * @version 1.0.0
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String itemName;
    private String itemDescription;
    private boolean isVeg;
    private Integer price;
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer quantity;
    private Integer restaurantId;
}
