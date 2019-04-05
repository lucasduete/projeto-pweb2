import { Component, OnInit } from '@angular/core';
import { ProfessorService } from './../../service/professor.service';
import { HorarioService } from './../../service/horario.service';
import { Routes, ActivatedRoute, Router } from '@angular/router';
import { Professor } from 'src/app/model/professor';

@Component({
  selector: 'app-professor',
  templateUrl: './professor.component.html',
  styleUrls: ['./professor.component.css']
})
export class ProfessorComponent implements OnInit {

  professores: any[];

  constructor(
    private professorService: ProfessorService,
    private horarioService: HorarioService,
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

  getHorario(id: number) {
    
  this.router.navigate(["/listaHorarioProfessor"], {queryParams: {prof: id}});
  }

  atualizar(professor: Professor) {
    this.router.navigate(['/addProfessor'], { queryParams: { prof: JSON.stringify(professor) } });
  }

  deletar(matricula: number) {
    let resp = confirm("Deseja deletar este professor?");
    if (resp == true) {
      this.professorService.deletarProfessor(matricula).subscribe(res => {
        if (res.status == 200) {
          this.getProfessores();
          alert("Professor deletado!");
        }
      });
    }

  }

}
