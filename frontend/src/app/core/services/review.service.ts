import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ReviewResponse {
  id: number;
  rating: number;
  comment: string;
  commentPt: string;
  authorUsername: string;
  createdAt: string;
}

export interface ReviewRequest {
  rating: number;
  comment: string;
  commentPt: string;
}

@Injectable({ providedIn: 'root' })
export class ReviewService {
  constructor(private http: HttpClient) {}

  findByRecipe(recipeId: number): Observable<ReviewResponse[]> {
    return this.http.get<ReviewResponse[]>(`/api/recipes/${recipeId}/reviews`);
  }

  create(recipeId: number, review: ReviewRequest): Observable<ReviewResponse> {
    return this.http.post<ReviewResponse>(`/api/recipes/${recipeId}/reviews`, review);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`/api/reviews/${id}`);
  }
}
