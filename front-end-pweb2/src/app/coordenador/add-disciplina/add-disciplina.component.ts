import { Component, OnInit } from '@angular/core';
import { CursoService } from './../../service/curso.service'
import { Disciplina } from 'src/app/model/disciplina';

@Component({
  selector: 'app-add-disciplina',
  templateUrl: './add-disciplina.component.html',
  styleUrls: ['./add-disciplina.component.css']
})
export class AddDisciplinaComponent implements OnInit {

  disciplinas : any[];

  disciplina: Disciplina = {
    codigo: null,
    nome: null
  }

  constructor(private cursoService: CursoService) { }

  ngOnInit() {
    this.disciplinas = this.cursoService.getDisciplina();
  }

  addDisciplina(){
    this.cursoService.addDisciplina(this.disciplina);
    console.log(this.disciplinas);
    this.disciplina = {
      codigo: null,
      nome: null
    }
    alert("Disciplina cadastrada!");
  }
}
