import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { path: '', loadComponent: () => import('./features/home/components/home-page/home-page.component').then(m => m.HomePageComponent) },
  { path: 'recipes', loadComponent: () => import('./features/recipes/components/recipe-list/recipe-list.component').then(m => m.RecipeListComponent) },
  { path: 'recipes/:id', loadComponent: () => import('./features/recipes/components/recipe-detail/recipe-detail.component').then(m => m.RecipeDetailComponent) },
  { path: 'my-recipes', loadComponent: () => import('./features/recipes/components/my-recipes/my-recipes.component').then(m => m.MyRecipesComponent), canActivate: [authGuard] },
  { path: 'add-recipe', loadComponent: () => import('./features/recipes/components/add-edit-recipe/add-edit-recipe.component').then(m => m.AddEditRecipeComponent), canActivate: [authGuard] },
  { path: 'edit-recipe/:id', loadComponent: () => import('./features/recipes/components/add-edit-recipe/add-edit-recipe.component').then(m => m.AddEditRecipeComponent), canActivate: [authGuard] },
  { path: 'login', loadComponent: () => import('./features/auth/components/login/login.component').then(m => m.LoginComponent) },
  { path: 'register', loadComponent: () => import('./features/auth/components/register/register.component').then(m => m.RegisterComponent) },
  { path: 'contact', loadComponent: () => import('./features/contact/components/contact-page/contact-page.component').then(m => m.ContactPageComponent) },
  { path: '**', redirectTo: '' }
];
