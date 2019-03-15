import { AmbienteService } from './../../ambiente.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Ambiente } from 'src/app/ambiente';

@Component({
  selector: 'app-lista-ambiente',
  templateUrl: './lista-ambiente.component.html',
  styleUrls: ['./lista-ambiente.component.css']
})
export class ListaAmbienteComponent implements OnInit {
  ambientes : Observable<Ambiente[]>
  constructor(
    private ambienteService : AmbienteService
  ) { 
    this.getAmbientes();
  }

  ngOnInit() {
  }

  getAmbientes(){
    this.ambientes = this.ambienteService.getAmbientes();
  }
}
