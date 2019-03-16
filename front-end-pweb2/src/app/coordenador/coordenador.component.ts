import swal from 'sweetalert';
import { Professor } from 'src/app/professor';
import { Ambiente } from 'src/app/ambiente';
import { ProfessorService } from './../professor.service';
import { CursoService } from './../curso.service';
import { Curso } from 'src/app/curso';
import { AmbienteService } from './../ambiente.service';
import { Component, OnInit } from '@angular/core';
import { CoordenadorService } from '../coordenador.service';
import { Coordenador } from '../coordenador';

@Component({
  selector: 'app-coordenador',
  templateUrl: './coordenador.component.html',
  styleUrls: ['./coordenador.component.css']
})
export class CoordenadorComponent implements OnInit {
  ambiente : Ambiente = {
    codigo : null,
    nome : ''
  };

  coordenador : Coordenador = {
    matricula : null,
    nome : '',
    senha : ''
  };

  curso : Curso = {
    codigo : null,
    nome : '',
    descricao : '',
    disciplinas : null
  };

  professor : Professor = {
    matricula : null,
    nome : ''
  };

  constructor(
    private ambienteService : AmbienteService,
    private coordendorService : CoordenadorService,
    private cursoService : CursoService,
    private professorService : ProfessorService
  ) { }

  ngOnInit() {
  }

  addAmbinete() : void{
    this.ambienteService.addAmbinete(this.ambiente).subscribe(
      data => {
        swal("Parabéns!", "Cadastro feito com sucesso!", "success");
      },
      error => {
        swal("Que pena!", "Não foi possível realizar o cadastro!", "error");
        this.ambiente = {
          codigo : null,
          nome : ''
        }
      }
    )
  }

  addCoordenador() : void{
    this.coordendorService.addCoordenador(this.coordenador).subscribe(
      data => {
        swal("Parabéns!", "Cadastro feito com sucesso!", "success");
      },
      error => {
        swal("Que pena!", "Não foi possível realizar o cadastro!", "error");
        this.coordenador = {
          matricula : null,
          nome : '',
          senha : ''
        }
      }
    )
  }

  addCurso() : void{
    this.cursoService.addCurso(this.curso).subscribe(
      data => {
        swal("Parabéns!", "Cadastro feito com sucesso!", "success");
      },
      error => {
        swal("Que pena!", "Não foi possível realizar o cadastro!", "error");
        this.curso = {
          codigo : null,
          nome : '',
          descricao : '',
          disciplinas : null
        }
      }
    )
  }

  addProfessor() : void{
    this.professorService.addProfessor(this.professor).subscribe(
      data => {
        swal("Parabéns!", "Cadastro feito com sucesso!", "success");
      },
      error => {
        swal("Que pena!", "Não foi possível realizar o cadastro!", "error");
        this.professor = {
          matricula : null,
          nome : ''
        }
      }
    )
  }

}
