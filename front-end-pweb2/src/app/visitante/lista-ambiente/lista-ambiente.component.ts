import { AmbienteService } from './../../service/ambiente.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Ambiente } from 'src/app/model/ambiente';

@Component({
  selector: 'app-lista-ambiente',
  templateUrl: './lista-ambiente.component.html',
  styleUrls: ['./lista-ambiente.component.css']
})
export class ListaAmbienteComponent implements OnInit {
  
  ambientes : any[];

  constructor(
    private ambienteService : AmbienteService
  ) { 
    this.getAmbientes();
  }

  ngOnInit() {
  }

  getAmbientes(){
    this.ambienteService.getAmbientes().subscribe(res=>{
      this.ambientes = res.body;
    })
  }
}
