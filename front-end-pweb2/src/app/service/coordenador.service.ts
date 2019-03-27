import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Coordenador } from './../model/coordenador';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class CoordenadorService {

  //private url = 'http://localhost:8080/coordenador';
  //private urlLogin = 'http://localhost:8080/login';
  private url = 'http://192.168.0.103:8080/coordenador';
  private urlLogin = 'http://192.168.0.103:8080/login';

  constructor(
    private http : HttpClient
  ) { }
  
  getCoordenador() : Observable<HttpResponse<Coordenador[]>>{
    return this.http.get<Coordenador[]>(this.url,{observe:'response'},);
  }

  getCoordenadorMatricula(matricula): Observable<HttpResponse<Coordenador>>{
    return this.http.get<Coordenador>(this.url + '/'+ matricula, {observe:'response'});
  }

  addCoordenador(coordenador : Coordenador) : Observable<HttpResponse<Coordenador>>{
    return this.http.post<Coordenador>(this.url, coordenador, {observe:'response'},);
  }

  login(coordenador : Coordenador) : Observable<HttpResponse<Coordenador>>{
    return this.http.post<Coordenador>(this.urlLogin, coordenador, {observe:'response'});
  }

}
