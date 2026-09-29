package com.portfolio.recipesharing.service;

import com.portfolio.recipesharing.dto.request.ReviewRequest;
import com.portfolio.recipesharing.dto.response.ReviewResponse;
import com.portfolio.recipesharing.entity.Recipe;
import com.portfolio.recipesharing.entity.Review;
import com.portfolio.recipesharing.entity.User;
import com.portfolio.recipesharing.exception.BadRequestException;
import com.portfolio.recipesharing.exception.ResourceNotFoundException;
import com.portfolio.recipesharing.exception.UnauthorizedException;
import com.portfolio.recipesharing.repository.RecipeRepository;
import com.portfolio.recipesharing.repository.ReviewRepository;
import com.portfolio.recipesharing.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, RecipeRepository recipeRepository,
                         UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public List<ReviewResponse> findByRecipe(Long recipeId) {
        return reviewRepository.findByRecipeIdOrderByCreatedAtDesc(recipeId)
                .stream().map(ReviewResponse::from).toList();
    }

    @Transactional
    public ReviewResponse create(Long recipeId, ReviewRequest request, String username) {
        if (request.rating() < 1 || request.rating() > 5) {
            throw new BadRequestException("Rating must be between 1 and 5");
        }
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + recipeId));
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        Review review = new Review();
        review.setRating(request.rating());
        review.setComment(request.comment());
        review.setCommentPt(request.commentPt());
        review.setRecipe(recipe);
        review.setAuthor(author);
        reviewRepository.save(review);

        Double avg = reviewRepository.getAverageRatingByRecipeId(recipeId);
        recipe.setAverageRating(avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0);
        recipeRepository.save(recipe);

        return ReviewResponse.from(review);
    }

    @Transactional
    public void delete(Long reviewId, String username) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + reviewId));
        if (!review.getAuthor().getUsername().equals(username)) {
            throw new UnauthorizedException("You can only delete your own reviews");
        }
        Long recipeId = review.getRecipe().getId();
        reviewRepository.delete(review);

        Double avg = reviewRepository.getAverageRatingByRecipeId(recipeId);
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe != null) {
            recipe.setAverageRating(avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0);
            recipeRepository.save(recipe);
        }
    }
}
