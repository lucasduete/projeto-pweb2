import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ambiente } from './ambiente';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AmbienteService {
  
  private url = 'localhost:8761/ambiente'
  
  constructor(
    private http : HttpClient
  ) { }
  
  getAmbientes() : Observable<Ambiente[]>{
    return this.http.get<Ambiente[]>(this.url);
  }

  addAmbinete(ambiente : Ambiente) : Observable<any>{
    return this.http.post<Ambiente>(this.url, ambiente);
  }

}
