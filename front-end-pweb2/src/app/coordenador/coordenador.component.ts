import { Component, OnInit, ViewChild } from '@angular/core';
import { CoordenadorService } from '../coordenador.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ListaCursoComponent } from '../visitante/lista-curso/lista-curso.component';

@Component({
  selector: 'app-coordenador',
  templateUrl: './coordenador.component.html',
  styleUrls: ['./coordenador.component.css']
})
export class CoordenadorComponent implements OnInit {

  @ViewChild(ListaCursoComponent) listaCurso: ListaCursoComponent;

  coordenadores : any[];

  constructor(
    private coordendorService: CoordenadorService,
    private router: ActivatedRoute
  ) {

  }
  ngOnInit() {
    this.getCoordenadores();
  }

  getCoordenadores(){
    this.coordendorService.getCoordenador().subscribe(res=>{
      this.coordenadores = res.body;
      console.log(this.coordenadores);
    })
  }
}
