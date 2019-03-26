import { Component, OnInit, ViewChild } from '@angular/core';
import { CoordenadorService } from '../../service/coordenador.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-coordenador',
  templateUrl: './coordenador.component.html',
  styleUrls: ['./coordenador.component.css']
})

export class CoordenadorComponent implements OnInit {

  coordenadores : any[];

  constructor(
    private coordendorService: CoordenadorService,
    private router: ActivatedRoute
  ) {

  }
  ngOnInit() {
    this.getCoordenadores();
  }

  getCoordenadores(){
    this.coordendorService.getCoordenador().subscribe(res=>{
      this.coordenadores = res.body;
      console.log(this.coordenadores);
    })
  }
}
