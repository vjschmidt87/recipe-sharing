import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { TranslationService } from '../../../../core/services/translation.service';
import { RecipeService, RecipeResponse } from '../../../../core/services/recipe.service';
import { RecipeCardComponent } from '../../../../shared/components/recipe-card/recipe-card.component';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [RouterLink, RecipeCardComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent implements OnInit {
  t = inject(TranslationService);
  private recipeService = inject(RecipeService);
  featured: RecipeResponse[] = [];

  ngOnInit(): void {
    this.recipeService.findFeatured().subscribe(r => this.featured = r);
  }
}
