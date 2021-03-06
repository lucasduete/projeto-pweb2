import { CoordenadorService } from './../../service/coordenador.service';
import { Coordenador } from './../../model/coordenador';

import { Component, OnInit } from '@angular/core';
import { Routes, ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  coordenador: Coordenador = {
    matricula:null,
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
      this.route.navigate(["/inicioCoordenador"]);    
      },
      error => {
        alert('Não foi possível realizar o login!');
      }
    );
}}
