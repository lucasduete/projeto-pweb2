import { Component, OnInit } from '@angular/core';
import { HorarioService } from './../../service/horario.service';
import { ActivatedRoute } from '@angular/router';
import { CodeNode } from 'source-list-map';
import { Horario } from 'src/app/model/horario';

@Component({
  selector: 'app-lista-horario-professor',
  templateUrl: './lista-horario-professor.component.html',
  styleUrls: ['./lista-horario-professor.component.css']
})
export class ListaHorarioProfessorComponent implements OnInit {
  horarios: any;
  private codProfessor: number;

  constructor(private horarioService: HorarioService, router: ActivatedRoute) {
    router.queryParams.subscribe(params => {
      if (params['prof']) {
        this.codProfessor = params['prof'];
        this.getHorarios();
      }
    });
  }

  getHorarios() {
    this.horarioService.getHorarioProfessor(this.codProfessor).subscribe(res => {
      this.horarios = res.body;
      console.log(JSON.stringify(res.body));
    })
  }

  ngOnInit() {

    this.horarios = this.horarioService.getHorario();
    console.log(this.horarios);
  }
}
