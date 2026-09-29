import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TranslationService } from '../../../../core/services/translation.service';
import { RecipeService, RecipeResponse } from '../../../../core/services/recipe.service';
import { RecipeCardComponent } from '../../../../shared/components/recipe-card/recipe-card.component';

@Component({
  selector: 'app-recipe-list',
  standalone: true,
  imports: [FormsModule, RecipeCardComponent],
  templateUrl: './recipe-list.component.html',
  styleUrl: './recipe-list.component.scss'
})
export class RecipeListComponent implements OnInit {
  t = inject(TranslationService);
  private recipeService = inject(RecipeService);

  recipes: RecipeResponse[] = [];
  search = '';
  selectedCuisine = '';
  selectedDietary = '';
  cuisines = ['ITALIAN', 'MEXICAN', 'ASIAN', 'AMERICAN', 'BRAZILIAN', 'MEDITERRANEAN', 'INDIAN', 'FRENCH', 'OTHER'];
  dietaryTags = ['VEGETARIAN', 'VEGAN', 'GLUTEN_FREE', 'DAIRY_FREE', 'NUT_FREE', 'LOW_CARB', 'HIGH_PROTEIN'];

  ngOnInit(): void {
    this.loadRecipes();
  }

  loadRecipes(): void {
    this.recipeService.findAll({
      cuisine: this.selectedCuisine || undefined,
      dietary: this.selectedDietary || undefined,
      search: this.search || undefined
    }).subscribe(r => this.recipes = r);
  }

  onFilterChange(): void {
    this.loadRecipes();
  }
}
