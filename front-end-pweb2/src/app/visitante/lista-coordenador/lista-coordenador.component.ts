import { CoordenadorService } from './../../service/coordenador.service';
import { Coordenador } from './../../model/coordenador';
import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-lista-coordenador',
  templateUrl: './lista-coordenador.component.html',
  styleUrls: ['./lista-coordenador.component.css']
})
export class ListaCoordenadorComponent implements OnInit {
  coordenadores : any[];

  constructor(
    private coordenadorService : CoordenadorService
  ) {
    this.getCoordenadores();
   }

  ngOnInit() {
  }

  getCoordenadores(){
    this.coordenadorService.getCoordenadorVisitante().subscribe(res=>{
      this.coordenadores = res.body;
    })
  }
}
