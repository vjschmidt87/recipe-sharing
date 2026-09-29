package com.portfolio.recipesharing.service;

import com.portfolio.recipesharing.dto.request.RecipeRequest;
import com.portfolio.recipesharing.dto.response.RecipeResponse;
import com.portfolio.recipesharing.entity.Recipe;
import com.portfolio.recipesharing.entity.User;
import com.portfolio.recipesharing.enums.CuisineType;
import com.portfolio.recipesharing.exception.ResourceNotFoundException;
import com.portfolio.recipesharing.exception.UnauthorizedException;
import com.portfolio.recipesharing.repository.RecipeRepository;
import com.portfolio.recipesharing.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public List<RecipeResponse> findAll(CuisineType cuisine, String dietary, Double minRating, String search) {
        return recipeRepository.findByFilters(cuisine, dietary, minRating, search)
                .stream().map(RecipeResponse::from).toList();
    }

    public RecipeResponse findById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + id));
        return RecipeResponse.from(recipe);
    }

    public List<RecipeResponse> findByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return recipeRepository.findByAuthorId(user.getId())
                .stream().map(RecipeResponse::from).toList();
    }

    public List<RecipeResponse> findFeatured() {
        return recipeRepository.findTop6ByOrderByAverageRatingDesc()
                .stream().map(RecipeResponse::from).toList();
    }

    @Transactional
    public RecipeResponse create(RecipeRequest request, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        Recipe recipe = new Recipe();
        mapRequest(request, recipe);
        recipe.setAuthor(author);
        recipe.setAverageRating(0.0);
        recipeRepository.save(recipe);
        return RecipeResponse.from(recipe);
    }

    @Transactional
    public RecipeResponse update(Long id, RecipeRequest request, String username) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + id));
        if (!recipe.getAuthor().getUsername().equals(username)) {
            throw new UnauthorizedException("You can only edit your own recipes");
        }
        mapRequest(request, recipe);
        recipeRepository.save(recipe);
        return RecipeResponse.from(recipe);
    }

    @Transactional
    public void delete(Long id, String username) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + id));
        if (!recipe.getAuthor().getUsername().equals(username)) {
            throw new UnauthorizedException("You can only delete your own recipes");
        }
        recipeRepository.delete(recipe);
    }

    private void mapRequest(RecipeRequest req, Recipe recipe) {
        recipe.setTitle(req.title());
        recipe.setTitlePt(req.titlePt());
        recipe.setDescription(req.description());
        recipe.setDescriptionPt(req.descriptionPt());
        recipe.setIngredients(req.ingredients());
        recipe.setIngredientsPt(req.ingredientsPt());
        recipe.setInstructions(req.instructions());
        recipe.setInstructionsPt(req.instructionsPt());
        recipe.setPrepTimeMinutes(req.prepTimeMinutes());
        recipe.setCookTimeMinutes(req.cookTimeMinutes());
        recipe.setServings(req.servings());
        recipe.setCuisine(req.cuisine());
        recipe.setDietaryTags(req.dietaryTags());
        recipe.setImageUrl(req.imageUrl());
    }
}
