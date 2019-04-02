import { Component, OnInit } from '@angular/core';
import { HorarioService } from './../../service/horario.service';

@Component({
  selector: 'app-lista-horario-ambiente',
  templateUrl: './lista-horario-ambiente.component.html',
  styleUrls: ['./lista-horario-ambiente.component.css']
})
export class ListaHorarioAmbienteComponent implements OnInit {
  horarios: any [];

  constructor(private horarioService : HorarioService) { }

  ngOnInit() {
    this.horarios = this.horarioService.getHorario();
    console.log(this.horarios);
  }
}
