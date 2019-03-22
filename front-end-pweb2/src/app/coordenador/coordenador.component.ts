import { Professor } from 'src/app/professor';
import { Ambiente } from 'src/app/ambiente';
import { ProfessorService } from './../professor.service';
import { CursoService } from './../curso.service';
import { Curso } from 'src/app/curso';
import { AmbienteService } from './../ambiente.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { CoordenadorService } from '../coordenador.service';
import { Coordenador } from '../coordenador';
import { Router, ActivatedRoute } from '@angular/router';
import { ListaCursoComponent } from '../visitante/lista-curso/lista-curso.component';

@Component({
  selector: 'app-coordenador',
  templateUrl: './coordenador.component.html',
  styleUrls: ['./coordenador.component.css']
})
export class CoordenadorComponent implements OnInit {

  @ViewChild(ListaCursoComponent) listaCurso: ListaCursoComponent;

  ambiente: Ambiente = {
    codigo: null,
    nome: ''
  };

  coordenador: Coordenador = {
    matricula: null,
    nome: '',
    senha: ''
  };

  curso: Curso = {
    codigo: null,
    nome: '',
    descricao: '',
    disciplinas: null
  };

  professor: Professor = {
    matricula: null,
    nome: ''
  };

  constructor(
    private ambienteService: AmbienteService,
    private router: ActivatedRoute,
    private coordendorService: CoordenadorService,
    private cursoService: CursoService,
    private professorService: ProfessorService
  ) {

  }
  ngOnInit() {
    this.listaCurso.getCursos();
    let mat = null;
    this.router.params.subscribe(params => {
        this.coordendorService.getCoordenadorMatricula(params['matricula']).subscribe(res=>{
          this.coordenador = res.body;
        });
    });
    
  }

  addAmbiente(): void {
    this.ambienteService.addAmbinete(this.ambiente).subscribe(
      data => {
        // swal('Parabéns!', 'Cadastro feito com sucesso!', 'success');

      },
      error => {
        // swal("Que pena!", "Não foi possível realizar o cadastro!", "error");
        this.ambiente = {
          codigo: null,
          nome: ''
        }
      }
    )
  }

  addCoordenador(): void {
    this.coordendorService.addCoordenador(this.coordenador).subscribe(
      data => {
        // swal("Parabéns!", "Cadastro feito com sucesso!", "success");
      },
      error => {
        // swal("Que pena!", "Não foi possível realizar o cadastro!", "error");
        this.coordenador = {
          matricula: null,
          nome: '',
          senha: ''
        }
      }
    )
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

  addProfessor(): void {
    console.log("professor");
    this.professorService.addProfessor(this.professor).subscribe(
      data => {
        // swal("Parabéns!", "Cadastro feito com sucesso!", "success");
      },
      error => {
        // swal("Que pena!", "Não foi possível realizar o cadastro!", "error");
        this.professor = {
          matricula: null,
          nome: ''
        }
      }
    )
  }

}
