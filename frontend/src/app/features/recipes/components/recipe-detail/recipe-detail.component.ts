import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DecimalPipe } from '@angular/common';
import { TranslationService } from '../../../../core/services/translation.service';
import { AuthService } from '../../../../core/services/auth.service';
import { RecipeService, RecipeResponse } from '../../../../core/services/recipe.service';
import { ReviewService, ReviewResponse } from '../../../../core/services/review.service';

@Component({
  selector: 'app-recipe-detail',
  standalone: true,
  imports: [RouterLink, FormsModule, DecimalPipe],
  templateUrl: './recipe-detail.component.html',
  styleUrl: './recipe-detail.component.scss'
})
export class RecipeDetailComponent implements OnInit {
  t = inject(TranslationService);
  auth = inject(AuthService);
  private recipeService = inject(RecipeService);
  private reviewService = inject(ReviewService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  recipe: RecipeResponse | null = null;
  reviews: ReviewResponse[] = [];
  newRating = 5;
  newComment = '';

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.recipeService.findById(id).subscribe(r => this.recipe = r);
    this.reviewService.findByRecipe(id).subscribe(r => this.reviews = r);
  }

  get title(): string {
    return this.t.lang() === 'pt-br' && this.recipe?.titlePt ? this.recipe.titlePt : this.recipe?.title || '';
  }

  get description(): string {
    return this.t.lang() === 'pt-br' && this.recipe?.descriptionPt ? this.recipe.descriptionPt : this.recipe?.description || '';
  }

  get ingredients(): string {
    return this.t.lang() === 'pt-br' && this.recipe?.ingredientsPt ? this.recipe.ingredientsPt : this.recipe?.ingredients || '';
  }

  get instructions(): string {
    return this.t.lang() === 'pt-br' && this.recipe?.instructionsPt ? this.recipe.instructionsPt : this.recipe?.instructions || '';
  }

  get cuisineLabel(): string {
    return this.recipe ? this.t.t(`cuisines.${this.recipe.cuisine}`) : '';
  }

  get dietaryLabels(): string[] {
    if (!this.recipe?.dietaryTags) return [];
    return this.recipe.dietaryTags.split(',').filter(t => t !== 'NONE').map(t => this.t.t(`dietary.${t.trim()}`));
  }

  get isOwner(): boolean {
    return this.auth.currentUser()?.username === this.recipe?.authorUsername;
  }

  getReviewComment(review: ReviewResponse): string {
    return this.t.lang() === 'pt-br' && review.commentPt ? review.commentPt : review.comment;
  }

  submitReview(): void {
    if (!this.recipe) return;
    this.reviewService.create(this.recipe.id, {
      rating: this.newRating,
      comment: this.newComment,
      commentPt: this.newComment
    }).subscribe(r => {
      this.reviews.push(r);
      this.newComment = '';
      this.newRating = 5;
      this.recipeService.findById(this.recipe!.id).subscribe(rec => this.recipe = rec);
    });
  }

  deleteRecipe(): void {
    if (!this.recipe || !confirm(this.t.t('recipe.deleteConfirm'))) return;
    this.recipeService.delete(this.recipe.id).subscribe(() => this.router.navigate(['/recipes']));
  }

  ratingStars(rating: number): string {
    return '★'.repeat(Math.round(rating)) + '☆'.repeat(5 - Math.round(rating));
  }
}
