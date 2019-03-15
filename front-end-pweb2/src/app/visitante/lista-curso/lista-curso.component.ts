import { CursoService } from './../../curso.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Curso } from 'src/app/curso';

@Component({
  selector: 'app-lista-curso',
  templateUrl: './lista-curso.component.html',
  styleUrls: ['./lista-curso.component.css']
})
export class ListaCursoComponent implements OnInit {
  cursos : Observable<Curso[]>;
  
  constructor(
    private cursoService : CursoService
  ) {
    this.getCursos();
  }

  ngOnInit() {
  }

  getCursos(){
    this.cursos = this.cursoService.getCurso();
  }
}
