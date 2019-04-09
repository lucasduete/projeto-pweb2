import { Component, OnInit } from '@angular/core';
import { HorarioService } from './../../service/horario.service';
import { Horario } from 'src/app/model/horario';

@Component({
  selector: 'app-lista-horario-ambiente',
  templateUrl: './lista-horario-ambiente.component.html',
  styleUrls: ['./lista-horario-ambiente.component.css']
})
export class ListaHorarioAmbienteComponent implements OnInit {
  horarios: Horario [];

  constructor(private horarioService : HorarioService) { }

  ngOnInit() {
    this.horarios = this.horarioService.getHorario();
    console.log(this.horarios);
  }
}
