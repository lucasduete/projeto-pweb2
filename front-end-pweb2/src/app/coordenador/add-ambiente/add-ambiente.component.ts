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

  ambiente: Ambiente = {
    codigo: null,
    nome: ''
  };

  constructor(
    private ambienteService: AmbienteService,
    private router: ActivatedRoute,
  ) { }

  ngOnInit() {
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
}
