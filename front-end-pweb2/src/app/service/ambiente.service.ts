import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ambiente } from './../model/ambiente';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AmbienteService {
  
  //private url = 'http://localhost:8080/ambiente';
  private url = 'http://192.168.0.122:8080/ambiente';
  
  constructor(
    private http : HttpClient
  ) { }
  
  getAmbientes() : Observable<HttpResponse<Ambiente[]>>{
    return this.http.get<Ambiente[]>(this.url, {observe:'response'});
  }

  addAmbinete(ambiente : Ambiente) : Observable<HttpResponse<Ambiente>>{
    return this.http.post<Ambiente>(this.url, ambiente, {observe:'response'});
  }

}
