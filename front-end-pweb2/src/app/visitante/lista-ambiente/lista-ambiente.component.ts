import { AmbienteService } from './../../service/ambiente.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Ambiente } from 'src/app/model/ambiente';
import { HorarioService } from './../../service/horario.service';
import { Routes, ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-lista-ambiente',
  templateUrl: './lista-ambiente.component.html',
  styleUrls: ['./lista-ambiente.component.css']
})
export class ListaAmbienteComponent implements OnInit {
  
  ambientes : Ambiente[];

  constructor(
    private ambienteService : AmbienteService,
    private horarioService : HorarioService,
    private router: Router
  ) { 
  }

  ngOnInit() {
    this.getAmbientes();
  }

  getAmbientes(){
    this.ambienteService.getAmbientesVisitante().subscribe(res=>{
      this.ambientes = res.body;
    })
  }
  
  getHorario(id: number){
    this.horarioService.getHorarioAmbiente(id).subscribe(
      res=>{
        this.horarioService.saveHorario(res.body);
        this.router.navigate(["/listaHorarioAmbiente"]);  
      }
    )
  }
}
