import { Component, OnInit } from '@angular/core';
import { ProfessorService } from './../../service/professor.service';

@Component({
  selector: 'app-professor',
  templateUrl: './professor.component.html',
  styleUrls: ['./professor.component.css']
})
export class ProfessorComponent implements OnInit {

  professores: any[];

  constructor(
    private professorService: ProfessorService
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
}
