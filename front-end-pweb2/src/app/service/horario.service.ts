import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Aula } from '../model/aula';
import { DiaLetivo } from '../model/diaLetivo';
import { Horario } from '../model/horario';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class HorarioService {

  aulas: Aula[] = [];
  diasLetivos: DiaLetivo[] = [];
  horarios: Horario[] = [];
  private url = 'http://localhost:8080/horario';

  constructor(
    private http : HttpClient
  ) { }

  addAula(aula: Aula){
    this.aulas.push(aula);
  }

  getAulas(){
    return this.aulas;
  }

  addDias(diaLetivo: DiaLetivo){
    this.diasLetivos.push(diaLetivo);
  }

  getDias(){
    return this.diasLetivos;
  }

  addHorario(horario : Horario) : Observable<HttpResponse<Horario>>{
    return this.http.post<Horario>(this.url, horario, {observe:'response'});
  }

  getHorarioCurso(id: number) : Observable<HttpResponse<Horario[]>>{
    return this.http.get<Horario[]>(this.url + '/curso/' + id, {observe: "response" });
  }

  getHorarioAmbiente(id: number) : Observable<HttpResponse<Horario[]>>{
    return this.http.get<Horario[]>(this.url + '/ambiente/' + id, {observe: "response" });
  }

  getHorarioProfessor(id: number) : Observable<HttpResponse<Horario[]>>{
    return this.http.get<Horario[]>(this.url + '/professor/' + id, {observe: "response" });
  }

  saveHorario(horarios : Horario []){
    this.horarios = horarios;
  }

  getHorario(){
    return this.horarios;
  }
}
