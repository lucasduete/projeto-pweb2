import { Component, OnInit } from '@angular/core';
import { HorarioService } from './../../service/horario.service';
import { Horario } from 'src/app/model/horario';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-lista-horario-curso',
  templateUrl: './lista-horario-curso.component.html',
  styleUrls: ['./lista-horario-curso.component.css']
})
export class ListaHorarioCursoComponent implements OnInit {

  horarios: any [];
  private codCurso: number;

  constructor(private horarioService : HorarioService, private actRouter: ActivatedRoute) {
    this.actRouter.queryParams.subscribe(params=>{
      if(params['curso']){
        this.codCurso = params['curso'];
        this.getHorarios();
      }
    })
  }

  ngOnInit() {
    console.log(this.horarios);
  }

  getHorarios(){
    this.horarioService.getHorarioCurso(this.codCurso).subscribe(res=>{
      this.horarios = res.body;
    });
  }

  deletar(horario: Horario){
    this.horarioService.deletarHorario(horario.id).subscribe(res=>{
        if(res.status == 200){
          this.getHorarios();
          alert("Horário deletado");
        }
    });
  }
}
