package com.portfolio.recipesharing.repository;

import com.portfolio.recipesharing.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRecipeIdOrderByCreatedAtDesc(Long recipeId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.recipe.id = :recipeId")
    Double getAverageRatingByRecipeId(@Param("recipeId") Long recipeId);

    long countByRecipeId(Long recipeId);
}
