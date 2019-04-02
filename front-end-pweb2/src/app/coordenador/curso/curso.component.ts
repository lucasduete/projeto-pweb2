import { Component, OnInit } from '@angular/core';
import { CursoService } from './../../service/curso.service';
import { Curso } from 'src/app/model/curso';
import { HorarioService } from './../../service/horario.service';
import { Routes, ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-curso',
  templateUrl: './curso.component.html',
  styleUrls: ['./curso.component.css']
})
export class CursoComponent implements OnInit {

  cursos : any[] = [];
  
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
    this.cursoService.getCurso().subscribe(res=>{
      this.cursos = res.body;
    });
    console.log(this.cursos);
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
