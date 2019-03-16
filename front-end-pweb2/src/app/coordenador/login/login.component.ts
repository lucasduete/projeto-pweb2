import { CoordenadorService } from './../../coordenador.service';
import { Coordenador } from './../../coordenador';

import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert';
import { Routes, ActivatedRoute, Router } from '@angular/router';
import { CoordenadorComponent } from '../coordenador.component';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


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


  login(): void {
    
    this.coordenadorService.login(this.coordenador).subscribe( data=>{
      this.route.navigate(["/coordenador", this.coordenador.matricula]);    
      },
      error => {
        swal("Que pena!", "Não foi possível realizar o login!", "error");
      }
    );
  

}}
