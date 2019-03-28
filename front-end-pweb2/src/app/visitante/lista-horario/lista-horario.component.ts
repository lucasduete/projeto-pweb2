import { Component, OnInit } from '@angular/core';
import { HorarioService } from './../../service/horario.service';

@Component({
  selector: 'app-lista-horario',
  templateUrl: './lista-horario.component.html',
  styleUrls: ['./lista-horario.component.css']
})
export class ListaHorarioComponent implements OnInit {

  horarios: any [];

  constructor( private horarioService : HorarioService) { }

  ngOnInit() {
    this.horarios = this.horarioService.getHorario();
  }

}
