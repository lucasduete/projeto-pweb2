import { HttpHeaders, HttpClient,HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Professor } from './../model/professor';
import {environment} from "../../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ProfessorService {
  
  //private url = 'http://localhost:8080/professor';
  private url = environment.apiUrl + 'professor';
  
  constructor(
    private http : HttpClient
  ) { }

  getProfessor() : Observable<HttpResponse<Professor[]>>{
    return this.http.get<Professor[]>(this.url, {observe:'response'});
  }

  addProfessor(professor : Professor) : Observable<HttpResponse<Professor>>{
    return this.http.post<Professor>(this.url, professor, {observe:'response'});
  }
}
