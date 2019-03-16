import { HttpHeaders, HttpClient,HttpResponse } from '@angular/common/http';
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
  
  private url = 'http://localhost:8080/professor';
  
  constructor(
    private http : HttpClient
  ) { }

  getProfessor() : Observable<HttpResponse<Professor[]>>{
    return this.http.get<Professor[]>(this.url, {observe:'response'});
  }

  addProfessor(professor : Professor) : Observable<any>{
    return this.http.post<Professor>(this.url, professor);
  }
}
