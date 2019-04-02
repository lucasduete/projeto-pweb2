import { Component, OnInit } from '@angular/core';
import { ProfessorService } from './../../service/professor.service';
import { HorarioService } from './../../service/horario.service';
import { Routes, ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-professor',
  templateUrl: './professor.component.html',
  styleUrls: ['./professor.component.css']
})
export class ProfessorComponent implements OnInit {

  professores: any[];

  constructor(
    private professorService: ProfessorService,
    private horarioService : HorarioService,
    private router: Router
  ) {
    this.getProfessores();
  }

  ngOnInit() {
  }

  getProfessores() {
    this.professorService.getProfessor().subscribe(res => {
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
