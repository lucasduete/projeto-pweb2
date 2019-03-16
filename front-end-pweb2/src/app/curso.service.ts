import { Curso } from './curso';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  private url = 'http://localhost:8080/curso';

  constructor(
    private http: HttpClient
  ) { }

  getCurso(): Observable<HttpResponse<Curso[]>>
    {
      return this.http.get<Curso[]>(this.url, { observe: "response" });
    }

addCurso(curso : Curso) : Observable < any > {
  return this.http.post<Curso>(this.url, curso);
}
}
