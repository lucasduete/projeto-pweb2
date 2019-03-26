import { Component, OnInit } from '@angular/core';
import { CursoService } from './../../curso.service';
import { Curso } from 'src/app/curso';

@Component({
  selector: 'app-curso',
  templateUrl: './curso.component.html',
  styleUrls: ['./curso.component.css']
})
export class CursoComponent implements OnInit {

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
