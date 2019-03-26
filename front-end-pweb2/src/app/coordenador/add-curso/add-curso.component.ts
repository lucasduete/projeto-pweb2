import { Component, OnInit, ViewChild } from '@angular/core';
import { CursoService } from './../../service/curso.service';
import { Curso } from 'src/app/model/curso';
import { ListaCursoComponent } from '../../visitante/lista-curso/lista-curso.component';

@Component({
  selector: 'app-add-curso',
  templateUrl: './add-curso.component.html',
  styleUrls: ['./add-curso.component.css']
})
export class AddCursoComponent implements OnInit {

  @ViewChild(ListaCursoComponent) listaCurso: ListaCursoComponent;

  curso: Curso = {
    codigo: null,
    nome: '',
    descricao: '',
    disciplinas: null
  };

  constructor(private cursoService: CursoService) { }

  ngOnInit() {
    this.listaCurso.getCursos();
  }

  addCurso(): void {
    this.cursoService.addCurso(this.curso).subscribe(
      data => {
        alert("Cadastro feito com sucesso!");
        //
        this.curso = {
          codigo: null,
          nome: '',
          descricao: '',
          disciplinas: null
        }
      },
      error => {
        alert("Não foi possível realizar o cadastro!");
        //
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
