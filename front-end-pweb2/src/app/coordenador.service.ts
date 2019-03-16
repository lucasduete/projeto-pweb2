import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Coordenador } from './coordenador';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CoordenadorService {

  private url = 'localhost:8761/coordenador';

  constructor(
    private http : HttpClient
  ) { }
  
  getCoordenador() : Observable<Coordenador[]>{
    return this.http.get<Coordenador[]>(this.url);
  }

  addCoordenador(coordenador : Coordenador) : Observable<any>{
    return this.http.post<Coordenador>(this.url, coordenador);
  }

  login(coordenador : Coordenador) : Observable<Coordenador>{
    return this.http.post<Coordenador>(this.url + '/login', coordenador);
  }

}
