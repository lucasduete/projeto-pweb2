import { CoordenadorService } from './../../coordenador.service';
import { Coordenador } from './../../coordenador';

import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  coodenador : Coordenador = {
    matricula : null,
    nome : '',
    senha : ''
  }
  
  constructor(
    private coordenadorService : CoordenadorService
  ) { }

  ngOnInit() {
  }

  login() : void{
    this.coordenadorService.login(this.coodenador).subscribe(
      data => {
        window.location.assign('/coordenador');
      },
      error => {
        swal("Que pena!", "Não foi possível realizar o login!", "error");
      }
    );
  }

}
