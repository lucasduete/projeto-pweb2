import { Component, OnInit } from '@angular/core';
import { AmbienteService } from './../../service/ambiente.service';
import { Ambiente } from 'src/app/model/ambiente';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-ambiente',
  templateUrl: './add-ambiente.component.html',
  styleUrls: ['./add-ambiente.component.css']
})
export class AddAmbienteComponent implements OnInit {

  ehUpdate: boolean = false;

  ambiente: Ambiente = {
    codigo: null,
    nome: ''
  };

  constructor(
    private ambienteService: AmbienteService,
    private router: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.router.queryParams.subscribe(params => {
      if (params['ambiente']) {
        this.ambiente = JSON.parse(params['ambiente']);
        this.ehUpdate = true;
      }
    });
  }

  addAmbiente(): void {
    this.ambienteService.addAmbinete(this.ambiente).subscribe(
      data => {
        alert('Cadastro feito com sucesso!');
        //
        this.ambiente = {
          codigo: null,
          nome: ''
        }
        //swal('Parabéns!', 'Cadastro feito com sucesso!', 'success');

      },
      error => {
        alert('Não foi possível realizar o cadastro!');
        // swal("Que pena!", "Não foi possível realizar o cadastro!", "error");
        this.ambiente = {
          codigo: null,
          nome: ''
        }
      }
    )
  }

  atualizar() {
    this.ambienteService.atualizar(this.ambiente).subscribe(res => {
      if (res.status == 200) {
        alert("Ambiente atualizado");
      }
    });
  }

  update() {
    return this.ehUpdate;
  }
}
