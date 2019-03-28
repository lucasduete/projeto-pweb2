import { CursoService } from './../../service/curso.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Curso } from 'src/app/model/curso';
import { HorarioService } from './../../service/horario.service';

@Component({
  selector: 'app-lista-curso',
  templateUrl: './lista-curso.component.html',
  styleUrls: ['./lista-curso.component.css']
})
export class ListaCursoComponent implements OnInit {
  cursos : any[] = [];
  
  constructor(
    private cursoService : CursoService,
    private horarioService : HorarioService
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

  getHorario(id: number){
    this.horarioService.getHorarioCurso(id).subscribe(
      res=>{
        this.horarioService.saveHorario(res.body);
      }
    )
  }
}
