package com.portfolio.recipesharing.entity;

import com.portfolio.recipesharing.enums.CuisineType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(name = "title_pt", nullable = false, length = 200)
    private String titlePt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "description_pt", nullable = false, columnDefinition = "TEXT")
    private String descriptionPt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String ingredients;

    @Column(name = "ingredients_pt", nullable = false, columnDefinition = "TEXT")
    private String ingredientsPt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String instructions;

    @Column(name = "instructions_pt", nullable = false, columnDefinition = "TEXT")
    private String instructionsPt;

    @Column(name = "prep_time_minutes")
    private Integer prepTimeMinutes;

    @Column(name = "cook_time_minutes")
    private Integer cookTimeMinutes;

    private Integer servings;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CuisineType cuisine;

    @Column(name = "dietary_tags", length = 200)
    private String dietaryTags;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getTitlePt() { return titlePt; }
    public void setTitlePt(String titlePt) { this.titlePt = titlePt; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDescriptionPt() { return descriptionPt; }
    public void setDescriptionPt(String descriptionPt) { this.descriptionPt = descriptionPt; }
    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }
    public String getIngredientsPt() { return ingredientsPt; }
    public void setIngredientsPt(String ingredientsPt) { this.ingredientsPt = ingredientsPt; }
    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
    public String getInstructionsPt() { return instructionsPt; }
    public void setInstructionsPt(String instructionsPt) { this.instructionsPt = instructionsPt; }
    public Integer getPrepTimeMinutes() { return prepTimeMinutes; }
    public void setPrepTimeMinutes(Integer prepTimeMinutes) { this.prepTimeMinutes = prepTimeMinutes; }
    public Integer getCookTimeMinutes() { return cookTimeMinutes; }
    public void setCookTimeMinutes(Integer cookTimeMinutes) { this.cookTimeMinutes = cookTimeMinutes; }
    public Integer getServings() { return servings; }
    public void setServings(Integer servings) { this.servings = servings; }
    public CuisineType getCuisine() { return cuisine; }
    public void setCuisine(CuisineType cuisine) { this.cuisine = cuisine; }
    public String getDietaryTags() { return dietaryTags; }
    public void setDietaryTags(String dietaryTags) { this.dietaryTags = dietaryTags; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }
    public Double getAverageRating() { return averageRating; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
