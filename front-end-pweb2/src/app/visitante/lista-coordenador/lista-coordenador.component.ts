import { CoordenadorService } from './../../coordenador.service';
import { Coordenador } from './../../coordenador';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-lista-coordenador',
  templateUrl: './lista-coordenador.component.html',
  styleUrls: ['./lista-coordenador.component.css']
})
export class ListaCoordenadorComponent implements OnInit {
  coordenadores : Observable<Coordenador[]>;
  constructor(
    private coordenadorService : CoordenadorService
  ) { }

  ngOnInit() {
  }

  getCoordenadores(){
    this.coordenadores = this.coordenadorService.getCoordenador();
  }
}
