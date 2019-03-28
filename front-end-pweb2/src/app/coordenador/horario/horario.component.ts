import { Component, OnInit } from '@angular/core';
import { Aula } from 'src/app/model/aula';
import { DiaLetivo } from 'src/app/model/diaLetivo';
import { Horario } from 'src/app/model/horario';
import { HorarioService } from './../../service/horario.service';

@Component({
  selector: 'app-horario',
  templateUrl: './horario.component.html',
  styleUrls: ['./horario.component.css']
})
export class HorarioComponent implements OnInit {

  diasLetivos: any [];
 
  horario: Horario = {
    codigoCurso: null,
    diasLetivos: null,
    numeroPeriodo: null
  };

  constructor(private horarioService: HorarioService) { }

  ngOnInit() {
    this.diasLetivos = this.horarioService.getDias();
  }

  addHorario(){
    this.horario.diasLetivos = this.diasLetivos;
    this.horarioService.addHorario(this.horario).subscribe(
      data => {
        alert("Cadastro feito com sucesso!");
        //
        this.horario = {
          codigoCurso: null,
          diasLetivos: null,
          numeroPeriodo: null
        };
      },
      error => {
        alert("Não foi possível realizar o cadastro!");
        this.horario = {
          codigoCurso: null,
          diasLetivos: null,
          numeroPeriodo: null
        };
      }
    )
  }
}
