import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ambiente } from './../model/ambiente';
import {environment} from "../../environments/environment";


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AmbienteService {
  
  //private url = 'http://localhost:8080/ambiente';
  private url = environment.apiUrl + 'ambiente';
  
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
