import { Component, OnInit, ViewChild } from '@angular/core';
import { CoordenadorService } from '../../service/coordenador.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Coordenador } from 'src/app/model/coordenador';

@Component({
  selector: 'app-coordenador',
  templateUrl: './coordenador.component.html',
  styleUrls: ['./coordenador.component.css']
})

export class CoordenadorComponent implements OnInit {

  coordenadores: any[];

  constructor(
    private coordendorService: CoordenadorService,
    private router: Router
  ) {

  }
  ngOnInit() {
    this.getCoordenadores();
  }

  deletar(matricula: number) {
    let resp = confirm("Deseja deletar este coordenador?");
    if (resp == true) {
      this.coordendorService.deletarCoordenador(matricula).subscribe(res => {
        if (res.status == 200) {
          this.getCoordenadores();
          alert("Coordenador deletado!");
        }
      });
    }

  }

  atualizar(coordenador: Coordenador) {
    this.router.navigate(['/addCoordenador'],{queryParams: {coord: JSON.stringify(coordenador)}});
  }

  getCoordenadores() {
    this.coordendorService.getCoordenador().subscribe(res => {
      this.coordenadores = res.body;
    })
  }
}
