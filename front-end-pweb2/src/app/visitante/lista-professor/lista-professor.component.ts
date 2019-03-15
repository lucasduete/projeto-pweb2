import { ProfessorService } from './../../professor.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Professor } from 'src/app/professor';

@Component({
  selector: 'app-lista-professor',
  templateUrl: './lista-professor.component.html',
  styleUrls: ['./lista-professor.component.css']
})
export class ListaProfessorComponent implements OnInit {
  professores : Observable<Professor[]>;

  constructor(
    private professorService : ProfessorService
  ) { 
    this.getProfessores();
  }

  ngOnInit() {
  }

  getProfessores(){
    this.professores = this.professorService.getProfessor();
  }
}
