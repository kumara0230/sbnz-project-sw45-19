import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ControllerService {
  
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getAllHeroNames(): any {
    return this.http.get<any>(`${this.baseUrl}/getAllHeroNames`)
  }

  sendRequest(myTeam: string[], oppositeTeam: string[], rank: string): any {
    const requestPayload = {
      myTeam: myTeam,
      oppositeTeam: oppositeTeam,
      rank: rank
    };
    return this.http.post<any>(`${this.baseUrl}/sendRequest`, requestPayload);
  }

  getRecommendedHeroes(): any {
    return this.http.get<any>(`${this.baseUrl}/getResult`)
  }

  getMyTeam(): any {
    return this.http.get<any>(`${this.baseUrl}/getMyTeam`)
  }

  getOppositeTeam(): any {
    return this.http.get<any>(`${this.baseUrl}/getOppositeTeam`)
  }
}
