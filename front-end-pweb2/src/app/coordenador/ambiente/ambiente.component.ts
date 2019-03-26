import { Component, OnInit } from '@angular/core';
import { AmbienteService } from './../../ambiente.service';

@Component({
  selector: 'app-ambiente',
  templateUrl: './ambiente.component.html',
  styleUrls: ['./ambiente.component.css']
})
export class AmbienteComponent implements OnInit {

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
