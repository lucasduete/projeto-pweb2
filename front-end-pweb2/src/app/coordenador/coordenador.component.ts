
import { CursoService } from './../curso.service';
import { Curso } from 'src/app/curso';
import { Component, OnInit, ViewChild } from '@angular/core';
import { CoordenadorService } from '../coordenador.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ListaCursoComponent } from '../visitante/lista-curso/lista-curso.component';

@Component({
  selector: 'app-coordenador',
  templateUrl: './coordenador.component.html',
  styleUrls: ['./coordenador.component.css']
})
export class CoordenadorComponent implements OnInit {

  @ViewChild(ListaCursoComponent) listaCurso: ListaCursoComponent;

  coordenadores : any[];

  curso: Curso = {
    codigo: null,
    nome: '',
    descricao: '',
    disciplinas: null
  };


  constructor(
    private coordendorService: CoordenadorService,
    private router: ActivatedRoute,
    private cursoService: CursoService
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

  addCurso(): void {
    this.cursoService.addCurso(this.curso).subscribe(
      data => {
        // swal("Parabéns!", "Cadastro feito com sucesso!", "success");
      },
      error => {
        // swal("Que pena!", "Não foi possível realizar o cadastro!", "error");
        this.curso = {
          codigo: null,
          nome: '',
          descricao: '',
          disciplinas: null
        }
      }
    )
    this.listaCurso.getCursos();
  }
}
