package com.portfolio.recipesharing.controller;

import com.portfolio.recipesharing.dto.request.RecipeRequest;
import com.portfolio.recipesharing.dto.response.RecipeResponse;
import com.portfolio.recipesharing.enums.CuisineType;
import com.portfolio.recipesharing.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponse>> findAll(
            @RequestParam(required = false) CuisineType cuisine,
            @RequestParam(required = false) String dietary,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(recipeService.findAll(cuisine, dietary, minRating, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.findById(id));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<RecipeResponse>> findFeatured() {
        return ResponseEntity.ok(recipeService.findFeatured());
    }

    @GetMapping("/my-recipes")
    public ResponseEntity<List<RecipeResponse>> findMyRecipes(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(recipeService.findByUser(user.getUsername()));
    }

    @PostMapping
    public ResponseEntity<RecipeResponse> create(@RequestBody RecipeRequest request,
                                                  @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(recipeService.create(request, user.getUsername()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponse> update(@PathVariable Long id,
                                                  @RequestBody RecipeRequest request,
                                                  @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(recipeService.update(id, request, user.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                        @AuthenticationPrincipal UserDetails user) {
        recipeService.delete(id, user.getUsername());
        return ResponseEntity.noContent().build();
    }
}
