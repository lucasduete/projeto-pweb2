import { ProfessorService } from './../../service/professor.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Professor } from 'src/app/model/professor';

@Component({
  selector: 'app-lista-professor',
  templateUrl: './lista-professor.component.html',
  styleUrls: ['./lista-professor.component.css']
})
export class ListaProfessorComponent implements OnInit {
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
