import { Component, OnInit } from '@angular/core';
import { Professor } from 'src/app/model/professor';
import { ProfessorService } from './../../service/professor.service';

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
