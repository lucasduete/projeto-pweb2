import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-coordenador',
  templateUrl: './menu-coordenador.component.html',
  styleUrls: ['./menu-coordenador.component.css']
})
export class MenuCoordenadorComponent implements OnInit {

  constructor(public router: Router) { }

  ngOnInit() {
  }

  sair(){
    console.log("saindo");
    localStorage.removeItem("token");
    window.location.assign("/");
  }
}
