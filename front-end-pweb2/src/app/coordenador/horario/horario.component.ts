import { Component, OnInit } from '@angular/core';
import { Aula } from 'src/app/model/aula';

@Component({
  selector: 'app-horario',
  templateUrl: './horario.component.html',
  styleUrls: ['./horario.component.css']
})
export class HorarioComponent implements OnInit {
  aulas: Aula[] = [];

  aula: Aula ={
    numeroAula : null,
    turno : "",
    horaInicio : null,
    horaFim : null,
    matriculaProfessor : "",
    codigoDisciplina : null,
    codigoAmbiente : null
  };

  display = 'none';

  constructor() { }

  ngOnInit() {
    console.log(this.aulas.length);
  }

  isDisplay() {
    if (this.display == 'block') {
      this.display = 'none';
    } else { 
      this.display = 'block'; 
    }
  }

  addAula(){
    this.aulas.push(this.aula);
    alert("Aula cadastrada!");
    //
    this.display = 'none';
    this.aula = {
      numeroAula : null,
      turno : "",
      horaInicio : null,
      horaFim : null,
      matriculaProfessor : "",
      codigoDisciplina : null,
      codigoAmbiente : null
    };
  }
}
