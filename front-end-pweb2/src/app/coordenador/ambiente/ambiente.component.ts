import { Component, OnInit } from '@angular/core';
import { AmbienteService } from './../../service/ambiente.service';
import { HorarioService } from './../../service/horario.service';
import { Routes, ActivatedRoute, Router } from '@angular/router';
import { Ambiente } from 'src/app/model/ambiente';

@Component({
  selector: 'app-ambiente',
  templateUrl: './ambiente.component.html',
  styleUrls: ['./ambiente.component.css']
})
export class AmbienteComponent implements OnInit {

  ambientes: any[];

  constructor(
    private ambienteService: AmbienteService,
    private horarioService: HorarioService,
    private router: Router
  ) {
    this.getAmbientes();
  }

  ngOnInit() {
  }

  getAmbientes() {
    this.ambienteService.getAmbientes().subscribe(res => {
      this.ambientes = res.body;
    });
  }

  getHorario(id: number) {
    this.horarioService.getHorarioAmbiente(id).subscribe(
      res => {
        this.horarioService.saveHorario(res.body);
        this.router.navigate(["/listaHorarioAmbiente"]);
      }
    )
  }

  atualizar(ambiente: Ambiente) {
    this.router.navigate(['/addAmbiente'], { queryParams: { ambiente: ambiente } });
  }

  deletar(codigo: number) {
    this.ambienteService.deletar(codigo).subscribe(res => {
      if (res.status == 200) {
        alert("Ambiente deletado");
      }
    });
  }
}
