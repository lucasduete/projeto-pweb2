import { Component, OnInit } from '@angular/core';
import { HorarioService } from './../../service/horario.service';

@Component({
  selector: 'app-lista-horario-professor',
  templateUrl: './lista-horario-professor.component.html',
  styleUrls: ['./lista-horario-professor.component.css']
})
export class ListaHorarioProfessorComponent implements OnInit {
  horarios: any [];

  constructor(private horarioService : HorarioService) { }

  ngOnInit() {
    this.horarios = this.horarioService.getHorario();
    console.log(this.horarios);
  }
}
