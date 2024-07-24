import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

interface AuthResponse {
  status: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = '';
  private loggedIn = false;

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, { username, password }).pipe(
      map(response => {
        if (response.status === 200) {
          this.loggedIn = true;
        }
        return response;
      })
    );
  }

  signup(username: string, email: string, role: String, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/signup`, { username, email, password });
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }
}
