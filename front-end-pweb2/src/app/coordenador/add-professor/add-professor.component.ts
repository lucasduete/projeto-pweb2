import { Component, OnInit } from '@angular/core';
import { Professor } from 'src/app/model/professor';
import { ProfessorService } from './../../service/professor.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-professor',
  templateUrl: './add-professor.component.html',
  styleUrls: ['./add-professor.component.css']
})
export class AddProfessorComponent implements OnInit {

  private ehUpdate: boolean = false;

  professor: Professor = {
    matricula: null,
    nome: ''
  };

  constructor(
    private professorService: ProfessorService,
    private actRouter: ActivatedRoute) { }

  ngOnInit() {
    this.actRouter.queryParams.subscribe(params => {
      if (params['prof']) {
        this.professor = JSON.parse(params['prof']);
        this.ehUpdate = true;
      }
    });
  }

  addProfessor() {

    this.professorService.addProfessor(this.professor).subscribe(
      data => {
        alert("Cadastro feito com sucesso!");
        //
        this.professor = {
          matricula: null,
          nome: ''
        }
      },
      error => {
        alert("Não foi possível realizar o cadastro!");
        this.professor = {
          matricula: null,
          nome: ''
        }
      }
    )
  }
  update() {
    return this.ehUpdate;
  }

  atualizar() {
    this.professorService.atualizarProfessor(this.professor).subscribe(res => {
      if(res.status ==200){
        alert("Professor atualizado!");
      }
    });
  }
}
