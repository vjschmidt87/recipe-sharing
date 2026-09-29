package com.portfolio.recipesharing.dto.response;

import com.portfolio.recipesharing.entity.Recipe;
import com.portfolio.recipesharing.enums.CuisineType;
import java.time.LocalDateTime;

public record RecipeResponse(
        Long id,
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
        String imageUrl,
        String authorUsername,
        Double averageRating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static RecipeResponse from(Recipe r) {
        return new RecipeResponse(
                r.getId(), r.getTitle(), r.getTitlePt(),
                r.getDescription(), r.getDescriptionPt(),
                r.getIngredients(), r.getIngredientsPt(),
                r.getInstructions(), r.getInstructionsPt(),
                r.getPrepTimeMinutes(), r.getCookTimeMinutes(), r.getServings(),
                r.getCuisine(), r.getDietaryTags(), r.getImageUrl(),
                r.getAuthor().getUsername(), r.getAverageRating(),
                r.getCreatedAt(), r.getUpdatedAt()
        );
    }
}
