import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface RecipeResponse {
  id: number;
  title: string;
  titlePt: string;
  description: string;
  descriptionPt: string;
  ingredients: string;
  ingredientsPt: string;
  instructions: string;
  instructionsPt: string;
  prepTimeMinutes: number;
  cookTimeMinutes: number;
  servings: number;
  cuisine: string;
  dietaryTags: string;
  imageUrl: string;
  authorUsername: string;
  averageRating: number;
  createdAt: string;
}

export interface RecipeRequest {
  title: string;
  titlePt: string;
  description: string;
  descriptionPt: string;
  ingredients: string;
  ingredientsPt: string;
  instructions: string;
  instructionsPt: string;
  prepTimeMinutes: number;
  cookTimeMinutes: number;
  servings: number;
  cuisine: string;
  dietaryTags: string;
  imageUrl: string;
}

@Injectable({ providedIn: 'root' })
export class RecipeService {
  constructor(private http: HttpClient) {}

  findAll(filters?: { cuisine?: string; dietary?: string; minRating?: number; search?: string }): Observable<RecipeResponse[]> {
    let params = new HttpParams();
    if (filters?.cuisine) params = params.set('cuisine', filters.cuisine);
    if (filters?.dietary) params = params.set('dietary', filters.dietary);
    if (filters?.minRating) params = params.set('minRating', filters.minRating.toString());
    if (filters?.search) params = params.set('search', filters.search);
    return this.http.get<RecipeResponse[]>('/api/recipes', { params });
  }

  findById(id: number): Observable<RecipeResponse> {
    return this.http.get<RecipeResponse>(`/api/recipes/${id}`);
  }

  findFeatured(): Observable<RecipeResponse[]> {
    return this.http.get<RecipeResponse[]>('/api/recipes/featured');
  }

  findMyRecipes(): Observable<RecipeResponse[]> {
    return this.http.get<RecipeResponse[]>('/api/recipes/my-recipes');
  }

  create(recipe: RecipeRequest): Observable<RecipeResponse> {
    return this.http.post<RecipeResponse>('/api/recipes', recipe);
  }

  update(id: number, recipe: RecipeRequest): Observable<RecipeResponse> {
    return this.http.put<RecipeResponse>(`/api/recipes/${id}`, recipe);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`/api/recipes/${id}`);
  }
}
