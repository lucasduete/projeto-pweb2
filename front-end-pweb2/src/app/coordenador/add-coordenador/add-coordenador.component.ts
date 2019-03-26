
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

  coordenador: Coordenador = {
    matricula:null, 
    nome:"", 
    senha :""
  };

  constructor(
    private coordenadorService: CoordenadorService,
    private route: Router
  ) { }

  ngOnInit() {
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
}
