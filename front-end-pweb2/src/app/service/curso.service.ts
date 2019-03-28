import { Curso } from './../model/curso';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Disciplina } from '../model/disciplina';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  disciplinas : Disciplina[] = [];

  //private url = 'http://localhost:8080/curso';
  private url = 'http://192.168.0.117:8080/curso';

  constructor(
    private http: HttpClient
  ) { }

  addDisciplina(disciplina: Disciplina){
    this.disciplinas.push(disciplina);
  }

  getDisciplina(){
    return  this.disciplinas;
  }

  getCurso(): Observable<HttpResponse<Curso[]>>
  {
    return this.http.get<Curso[]>(this.url, { observe: "response" });
  }

  addCurso(curso : Curso) : Observable<HttpResponse<Curso>> {
    return this.http.post<Curso>(this.url, curso, {observe:'response'});
  }
}
