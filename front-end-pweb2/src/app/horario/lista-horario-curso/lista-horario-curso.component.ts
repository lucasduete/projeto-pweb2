import { Component, OnInit } from '@angular/core';
import { HorarioService } from './../../service/horario.service';

@Component({
  selector: 'app-lista-horario-curso',
  templateUrl: './lista-horario-curso.component.html',
  styleUrls: ['./lista-horario-curso.component.css']
})
export class ListaHorarioCursoComponent implements OnInit {

  horarios: any [];

  constructor(private horarioService : HorarioService) { }

  ngOnInit() {
    this.horarios = this.horarioService.getHorario();
    console.log(this.horarios);
  }
}
