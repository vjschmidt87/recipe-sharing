import { Component, Input, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DecimalPipe } from '@angular/common';
import { TranslationService } from '../../../core/services/translation.service';
import { RecipeResponse } from '../../../core/services/recipe.service';

@Component({
  selector: 'app-recipe-card',
  standalone: true,
  imports: [RouterLink, DecimalPipe],
  templateUrl: './recipe-card.component.html',
  styleUrl: './recipe-card.component.scss'
})
export class RecipeCardComponent {
  @Input({ required: true }) recipe!: RecipeResponse;
  t = inject(TranslationService);

  get title(): string {
    return this.t.lang() === 'pt-br' && this.recipe.titlePt ? this.recipe.titlePt : this.recipe.title;
  }

  get description(): string {
    return this.t.lang() === 'pt-br' && this.recipe.descriptionPt ? this.recipe.descriptionPt : this.recipe.description;
  }

  get cuisineLabel(): string {
    return this.t.t(`cuisines.${this.recipe.cuisine}`);
  }

  get totalTime(): number {
    return this.recipe.prepTimeMinutes + this.recipe.cookTimeMinutes;
  }

  get ratingStars(): string {
    const rating = this.recipe.averageRating || 0;
    return '★'.repeat(Math.round(rating)) + '☆'.repeat(5 - Math.round(rating));
  }
}
