package com.diarpy.foodcatalogueservice;

import com.diarpy.foodcatalogueservice.entity.FoodItem;
import com.diarpy.foodcatalogueservice.repository.FoodItemRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mack_TB
 * @since 29/09/2024
 * @version 1.0.0
 */

@SpringBootApplication
public class FoodCatalogueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodCatalogueServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*@Bean
    CommandLineRunner loadData(FoodItemRepo foodItemRepository) {
        return args -> {
            // Create FoodItem objects for the first restaurant (3 items)
            FoodItem foodItem1 = new FoodItem(null, "Couscous Royale", "Steamed semolina with vegetables and meats", true, 4000, 0, 1);
            FoodItem foodItem2 = new FoodItem(null, "Tagine de Poulet", "Chicken tagine with olives and lemon", false, 5000, 0, 1);
            FoodItem foodItem3 = new FoodItem(null, "Baklava", "Sweet pastry filled with nuts", true, 1500, 0, 1);

            // Create FoodItem objects for the rest of the restaurants (2 items each)
            FoodItem foodItem4 = new FoodItem(null, "Attiéké Poisson", "Attiéké with grilled fish", false, 2500, 0, 2);
            FoodItem foodItem5 = new FoodItem(null, "Poulet Braisé", "Braised chicken with spices", false, 3000, 0, 2);

            FoodItem foodItem6 = new FoodItem(null, "Yassa Poulet", "Chicken marinated in onions and lemon", false, 3500, 0, 3);
            FoodItem foodItem7 = new FoodItem(null, "Tiep Bou Dien", "Senegalese fish and rice", false, 4000, 0, 3);

            FoodItem foodItem8 = new FoodItem(null, "Pizza Margherita", "Classic pizza with tomato and mozzarella", true, 2000, 0, 4);
            FoodItem foodItem9 = new FoodItem(null, "Pizza Pepperoni", "Pizza with spicy pepperoni", false, 2500, 0, 4);

            FoodItem foodItem10 = new FoodItem(null, "Burger Maison", "House special beef burger", false, 3000, 0, 5);
            FoodItem foodItem11 = new FoodItem(null, "Salade César", "Classic Caesar salad with chicken", false, 2000, 0, 5);

            FoodItem foodItem12 = new FoodItem(null, "Sushi Maki", "Rolled sushi with tuna", false, 4500, 0, 6);
            FoodItem foodItem13 = new FoodItem(null, "Sashimi Saumon", "Fresh salmon sashimi", false, 6000, 0, 6);

            FoodItem foodItem14 = new FoodItem(null, "Sandwich Jambon", "Ham sandwich with fresh vegetables", false, 1500, 0, 7);
            FoodItem foodItem15 = new FoodItem(null, "Croissant Beurre", "Buttery croissant", true, 500, 0, 7);

            FoodItem foodItem16 = new FoodItem(null, "Poulet Grillé", "Grilled chicken with herbs", false, 3000, 0, 8);
            FoodItem foodItem17 = new FoodItem(null, "Frites Maison", "Home-style French fries", true, 1000, 0, 8);

            // Save FoodItems in the repository
            List<FoodItem> foodItems = Arrays.asList(foodItem1, foodItem2, foodItem3, foodItem4, foodItem5, foodItem6, foodItem7, foodItem8, foodItem9, foodItem10,
                    foodItem11, foodItem12, foodItem13, foodItem14, foodItem15, foodItem16, foodItem17);

            foodItemRepository.saveAll(foodItems);
        };
    }*/
}
