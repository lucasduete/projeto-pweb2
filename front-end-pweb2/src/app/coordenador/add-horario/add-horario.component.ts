import { Component, OnInit, Input } from '@angular/core';
import { DiaLetivo } from 'src/app/model/diaLetivo';
import { Aula } from 'src/app/model/aula';
import { HorarioService } from './../../service/horario.service';

@Component({
  selector: 'app-add-horario',
  templateUrl: './add-horario.component.html',
  styleUrls: ['./add-horario.component.css']
})
export class AddHorarioComponent implements OnInit {
  
  aulas : any [];
  dias: any [];

  diaLetivo: DiaLetivo = {
    dia : null,
    aulas : null
  };

  constructor(private horarioService : HorarioService) { 
  }

  ngOnInit() {
    this.aulas = this.horarioService.getAulas();
    this.dias = this.horarioService.getDias();
    console.log(this.aulas);
  }

  addDia(){
    this.diaLetivo.aulas = this.aulas;
    this.horarioService.addDias(this.diaLetivo);
    console.log(this.dias);
    alert("Dia Letivo cadastrado");
    this.diaLetivo = {
      dia : null,
      aulas : this.aulas
    }
  }
}
