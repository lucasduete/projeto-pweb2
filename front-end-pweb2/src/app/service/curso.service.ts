import { Curso } from './../model/curso';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { Disciplina } from '../model/disciplina';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  disciplinas: Disciplina[] = [];

  private url = environment.apiUrl + 'curso';

  constructor(
    private http: HttpClient
  ) { }

  addDisciplina(disciplina: Disciplina) {
    this.disciplinas.push(disciplina);
  }

  getDisciplina() {
    return this.disciplinas;
  }

  getCurso(): Observable<HttpResponse<Curso[]>> {
    return this.http.get<Curso[]>(this.url, { observe: "response" });
  }

  addCurso(curso: Curso): Observable<HttpResponse<Curso>> {
    return this.http.post<Curso>(this.url, curso, { observe: 'response' });
  }

  deletarCurso(codigo: number): Observable<HttpResponse<Curso>> {
    return this.http.delete<Curso>(this.url + '/' + codigo, { observe: 'response' });
  }

  atualizarCurso(curso: Curso): Observable<HttpResponse<Curso>> {
    return this.http.put<Curso>(this.url + '/' + curso.codigo, curso, { observe: 'response' });
  }
}
