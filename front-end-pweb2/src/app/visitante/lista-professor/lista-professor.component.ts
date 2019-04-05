import { ProfessorService } from './../../service/professor.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Professor } from 'src/app/model/professor';
import { HorarioService } from './../../service/horario.service';
import { Routes, ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-lista-professor',
  templateUrl: './lista-professor.component.html',
  styleUrls: ['./lista-professor.component.css']
})
export class ListaProfessorComponent implements OnInit {
  professores: any[];

  constructor(
    private professorService: ProfessorService,
    private horarioService : HorarioService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.getProfessores();
  }

  getProfessores() {
    this.professorService.getProfessoresVisitante().subscribe(res => {
      this.professores = res.body;
    })
  }

  getHorario(id: number){
    this.horarioService.getHorarioProfessor(id).subscribe(
      res=>{
        this.horarioService.saveHorario(res.body);
        this.router.navigate(["/listaHorarioProfessor"]);  
      }
    )
  }
}
