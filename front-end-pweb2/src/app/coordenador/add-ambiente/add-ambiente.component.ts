import { Component, OnInit } from '@angular/core';
import { AmbienteService } from './../../ambiente.service';
import { Ambiente } from 'src/app/ambiente';
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
        // swal('Parabéns!', 'Cadastro feito com sucesso!', 'success');

      },
      error => {
        // swal("Que pena!", "Não foi possível realizar o cadastro!", "error");
        this.ambiente = {
          codigo: null,
          nome: ''
        }
      }
    )
  }
}
