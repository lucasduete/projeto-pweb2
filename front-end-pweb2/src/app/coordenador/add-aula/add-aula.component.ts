import { Component, OnInit } from '@angular/core';
import { Aula } from 'src/app/model/aula';
import { HorarioService } from './../../service/horario.service'

@Component({
  selector: 'app-add-aula',
  templateUrl: './add-aula.component.html',
  styleUrls: ['./add-aula.component.css']
})
export class AddAulaComponent implements OnInit {

  aulas: any[];

  aula: Aula ={
    numeroAula : null,
    turno : "",
    horaInicio : null,
    horaFim : null,
    matriculaProfessor : "",
    codigoDisciplina : null,
    codigoAmbiente : null
  };
  
  constructor(private horarioService : HorarioService) { }

  ngOnInit() {
    this.aulas = this.horarioService.getAulas();
  }

  addAula(){
    this.horarioService.addAula(this.aula);
    console.log(this.aulas);
    alert("Aula cadastrada!");
    //
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