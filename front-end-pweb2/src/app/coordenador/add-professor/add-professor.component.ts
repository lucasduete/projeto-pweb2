import { Component, OnInit } from '@angular/core';
import { Professor } from 'src/app/professor';
import { ProfessorService } from './../../professor.service';

@Component({
  selector: 'app-add-professor',
  templateUrl: './add-professor.component.html',
  styleUrls: ['./add-professor.component.css']
})
export class AddProfessorComponent implements OnInit {

  professor: Professor = {
    matricula: null,
    nome: ''
  };

  constructor(private professorService: ProfessorService) { }

  ngOnInit() {
  }

  addProfessor(): void {
    console.log("professor");
    this.professorService.addProfessor(this.professor).subscribe(
      data => {
        // swal("Parabéns!", "Cadastro feito com sucesso!", "success");
      },
      error => {
        // swal("Que pena!", "Não foi possível realizar o cadastro!", "error");
        this.professor = {
          matricula: null,
          nome: ''
        }
      }
    )
  }

}
