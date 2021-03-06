import { CursoService } from './../../service/curso.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Curso } from 'src/app/model/curso';
import { HorarioService } from './../../service/horario.service';
import { Routes, ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-lista-curso',
  templateUrl: './lista-curso.component.html',
  styleUrls: ['./lista-curso.component.css']
})
export class ListaCursoComponent implements OnInit {
  cursos : Curso[];
  
  constructor(
    private cursoService : CursoService,
    private horarioService : HorarioService,
    private router: Router
  ) {
    
  }

  ngOnInit() {
    this.getCursos();
  }

  getCursos(){
    this.cursoService.getCursoVisitante().subscribe(res=>{
      this.cursos = res.body;
      console.log(this.cursos);
    });
  }

  getHorario(id: number){
    this.horarioService.getHorarioCurso(id).subscribe(
      res=>{
        this.horarioService.saveHorario(res.body);
        this.router.navigate(["/listaHorarioCurso"]);  
      }
    )
  }
}
