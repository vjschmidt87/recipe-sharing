package com.portfolio.recipesharing.repository;

import com.portfolio.recipesharing.entity.Recipe;
import com.portfolio.recipesharing.enums.CuisineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByAuthorId(Long authorId);

    @Query("SELECT r FROM Recipe r WHERE " +
           "(:cuisine IS NULL OR r.cuisine = :cuisine) AND " +
           "(:dietary IS NULL OR r.dietaryTags LIKE CONCAT('%', CAST(:dietary AS string), '%')) AND " +
           "(:minRating IS NULL OR r.averageRating >= :minRating) AND " +
           "(:search IS NULL OR LOWER(CAST(r.title AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) " +
           "OR LOWER(CAST(r.description AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) " +
           "OR LOWER(CAST(r.titlePt AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))" +
           " ORDER BY r.createdAt DESC")
    List<Recipe> findByFilters(
            @Param("cuisine") CuisineType cuisine,
            @Param("dietary") String dietary,
            @Param("minRating") Double minRating,
            @Param("search") String search);

    List<Recipe> findTop6ByOrderByAverageRatingDesc();
}
