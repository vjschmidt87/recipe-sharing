import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { TranslationService } from '../../../../core/services/translation.service';
import { RecipeService, RecipeResponse } from '../../../../core/services/recipe.service';
import { RecipeCardComponent } from '../../../../shared/components/recipe-card/recipe-card.component';

@Component({
  selector: 'app-my-recipes',
  standalone: true,
  imports: [RouterLink, RecipeCardComponent],
  templateUrl: './my-recipes.component.html',
  styleUrl: './my-recipes.component.scss'
})
export class MyRecipesComponent implements OnInit {
  t = inject(TranslationService);
  private recipeService = inject(RecipeService);
  recipes: RecipeResponse[] = [];

  ngOnInit(): void {
    this.recipeService.findMyRecipes().subscribe(r => this.recipes = r);
  }
}
