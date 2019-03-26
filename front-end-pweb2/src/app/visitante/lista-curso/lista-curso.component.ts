import { CursoService } from './../../service/curso.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Curso } from 'src/app/model/curso';

@Component({
  selector: 'app-lista-curso',
  templateUrl: './lista-curso.component.html',
  styleUrls: ['./lista-curso.component.css']
})
export class ListaCursoComponent implements OnInit {
  cursos : any[] = [];
  
  constructor(
    private cursoService : CursoService
  ) {
    
  }

  ngOnInit() {
    this.getCursos();
  }

  getCursos(){
    this.cursoService.getCurso().subscribe(res=>{
      this.cursos = res.body;
    });
    console.log(this.cursos);

  }
}
