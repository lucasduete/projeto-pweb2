import { Component, OnInit, ViewChild } from '@angular/core';
import { CursoService } from './../../curso.service';
import { Curso } from 'src/app/curso';
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
