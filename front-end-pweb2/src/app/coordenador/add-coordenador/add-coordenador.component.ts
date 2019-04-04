
import { Component, OnInit } from '@angular/core';
import { CoordenadorService } from './../../service/coordenador.service';
import { Coordenador } from './../../model/coordenador';
import { Routes, ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-coordenador',
  templateUrl: './add-coordenador.component.html',
  styleUrls: ['./add-coordenador.component.css']
})
export class AddCoordenadorComponent implements OnInit {

  ehUpdate: boolean = false;

  coordenador: Coordenador = {
    matricula: null,
    nome: "",
    senha: ""
  };

  constructor(
    private coordenadorService: CoordenadorService,
    private route: Router,
    private actRouter: ActivatedRoute
  ) { }

  ngOnInit() {
    this.actRouter.queryParams.subscribe(params => {
      if (params['coord']) {
        this.coordenador = JSON.parse(params['coord']);
        this.ehUpdate = true;
      }
      console.log(this.ehUpdate + "");
    });
  }

  addCoordenador(): void {
    this.coordenadorService.addCoordenador(this.coordenador).subscribe(
      data => {
        alert("Cadastro feito com sucesso!");
        //
        this.coordenador = {
          matricula: null,
          nome: '',
          senha: ''
        }
      },
      error => {
        alert("Não foi possível realizar o cadastro!");
        //
        this.coordenador = {
          matricula: null,
          nome: '',
          senha: ''
        }
      }
    )
  }


  update() {
    return this.ehUpdate;
  }

  atualizar() {
    this.coordenadorService.atualizarCoordenador(this.coordenador).subscribe(res => {
      if(res.status ==200){
        alert("Coordenador atualizado!");
      }
    });
  }
}
