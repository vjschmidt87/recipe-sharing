import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

interface AuthResponse {
  token: string;
  username: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  currentUser = signal<{ username: string; token: string } | null>(this.loadUser());

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>('/api/auth/login', { username, password }).pipe(
      tap(res => this.setUser(res))
    );
  }

  register(username: string, email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>('/api/auth/register', { username, email, password }).pipe(
      tap(res => this.setUser(res))
    );
  }

  logout(): void {
    this.currentUser.set(null);
    localStorage.removeItem('auth_token');
    localStorage.removeItem('auth_user');
  }

  isLoggedIn(): boolean {
    return this.currentUser() !== null;
  }

  getToken(): string | null {
    return localStorage.getItem('auth_token');
  }

  private setUser(res: AuthResponse): void {
    this.currentUser.set({ username: res.username, token: res.token });
    localStorage.setItem('auth_token', res.token);
    localStorage.setItem('auth_user', res.username);
  }

  private loadUser(): { username: string; token: string } | null {
    const token = localStorage.getItem('auth_token');
    const username = localStorage.getItem('auth_user');
    return token && username ? { token, username } : null;
  }
}
