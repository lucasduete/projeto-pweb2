import { Component, OnInit, ViewChild } from '@angular/core';
import { CursoService } from './../../service/curso.service';
import { Curso } from 'src/app/model/curso';
import { ListaCursoComponent } from '../../visitante/lista-curso/lista-curso.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-curso',
  templateUrl: './add-curso.component.html',
  styleUrls: ['./add-curso.component.css']
})
export class AddCursoComponent implements OnInit {

  disciplinas: any[];
  ehUpdate: boolean = false;
  curso: Curso = {
    codigo: null,
    nome: '',
    descricao: '',
    disciplinas: null
  };

  constructor(private cursoService: CursoService, private router: ActivatedRoute) { }

  ngOnInit() {
    this.disciplinas = this.cursoService.getDisciplina();
    this.router.queryParams.subscribe(params => {
      if (params['curso']) {
        this.curso = JSON.parse(params['curso']);
        this.disciplinas = this.curso.disciplinas;
        this.ehUpdate = true;
      }
    });
  }

  addCurso(): void {
    this.curso.disciplinas = this.disciplinas;
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
  }

  atualizar(){
    this.cursoService.atualizarCurso(this.curso).subscribe(res=>{
      if(res.status == 200){
        alert("Curso atualizado!");
      }
    })
  }

  update() {
    return this.ehUpdate;
  }
}
