import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Professor } from './professor';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ProfessorService {
  
  private url = 'localhost:8761/professor';
  
  constructor(
    private http : HttpClient
  ) { }

  getProfessor() : Observable<Professor[]>{
    return this.http.get<Professor[]>(this.url);
  }

  addProfessor(professor : Professor) : Observable<any>{
    return this.http.post<Professor>(this.url, professor);
  }
}
