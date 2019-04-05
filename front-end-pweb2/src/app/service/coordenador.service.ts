import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Coordenador } from './../model/coordenador';
import { environment } from "../../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CoordenadorService {

  private url = environment.apiUrl + 'api/coordenador';
  private urlLogin = environment.apiUrl + 'login';

  constructor(
    private http: HttpClient
  ) { }

  getCoordenador(): Observable<HttpResponse<Coordenador[]>> {
    return this.http.get<Coordenador[]>(this.url, { observe: 'response' });
  }

  getCoordenadorMatricula(matricula): Observable<HttpResponse<Coordenador>> {
    return this.http.get<Coordenador>(this.url + '/' + matricula, { observe: 'response' });
  }

  addCoordenador(coordenador: Coordenador): Observable<HttpResponse<Coordenador>> {
    return this.http.post<Coordenador>(this.url, coordenador, { observe: 'response' });
  }

  login(coordenador: Coordenador): Observable<HttpResponse<Coordenador>> {
    return this.http.post<Coordenador>(this.urlLogin, coordenador, { observe: 'response' });
  }

  deletarCoordenador(matricula: number): Observable<HttpResponse<Coordenador>> {
        return this.http.delete(this.url + '/'+ matricula, {observe:'response'});
  }

  atualizarCoordenador(coordenador: Coordenador): Observable<HttpResponse<Coordenador>>{
    return this.http.put(this.url + '/'+ coordenador.matricula, coordenador, {observe:'response'});
  }

}
