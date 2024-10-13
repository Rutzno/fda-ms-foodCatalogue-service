package com.diarpy.foodcatalogueservice.mapper;

import com.diarpy.foodcatalogueservice.dto.FoodItemDTO;
import com.diarpy.foodcatalogueservice.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Mack_TB
 * @since 29/09/2024
 * @version 1.0.0
 */

@Mapper
public interface FoodItemMapper {
    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItemDTO toFoodItemDTO(FoodItem foodItem);
    FoodItem toFoodItem(FoodItemDTO foodItemDTO);
}
