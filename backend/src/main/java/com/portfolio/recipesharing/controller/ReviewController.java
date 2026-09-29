package com.portfolio.recipesharing.controller;

import com.portfolio.recipesharing.dto.request.ReviewRequest;
import com.portfolio.recipesharing.dto.response.ReviewResponse;
import com.portfolio.recipesharing.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/recipes/{recipeId}/reviews")
    public ResponseEntity<List<ReviewResponse>> findByRecipe(@PathVariable Long recipeId) {
        return ResponseEntity.ok(reviewService.findByRecipe(recipeId));
    }

    @PostMapping("/recipes/{recipeId}/reviews")
    public ResponseEntity<ReviewResponse> create(@PathVariable Long recipeId,
                                                  @RequestBody ReviewRequest request,
                                                  @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(reviewService.create(recipeId, request, user.getUsername()));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                        @AuthenticationPrincipal UserDetails user) {
        reviewService.delete(id, user.getUsername());
        return ResponseEntity.noContent().build();
    }
}
