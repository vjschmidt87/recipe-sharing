package com.portfolio.recipesharing.dto.request;

import com.portfolio.recipesharing.enums.CuisineType;

public record RecipeRequest(
        String title,
        String titlePt,
        String description,
        String descriptionPt,
        String ingredients,
        String ingredientsPt,
        String instructions,
        String instructionsPt,
        Integer prepTimeMinutes,
        Integer cookTimeMinutes,
        Integer servings,
        CuisineType cuisine,
        String dietaryTags,
        String imageUrl
) {}
