import { Component, OnInit } from '@angular/core';
import { CursoService } from './../../service/curso.service';
import { Curso } from 'src/app/model/curso';
import { HorarioService } from './../../service/horario.service';
import { Routes, ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-curso',
  templateUrl: './curso.component.html',
  styleUrls: ['./curso.component.css']
})
export class CursoComponent implements OnInit {

  cursos: any[] = [];

  constructor(
    private cursoService: CursoService,
    private horarioService: HorarioService,
    private router: Router
  ) {

  }

  ngOnInit() {
    this.getCursos();
  }

  getCursos() {
    this.cursoService.getCurso().subscribe(res => {
      this.cursos = res.body;
    });
    console.log(this.cursos);
  }

  deletar(codigo: number) {
    let opcao = confirm("Deseja deletar este curso?");
    if (opcao) {
      this.cursoService.deletarCurso(codigo).subscribe(res => {
        if (res.status == 200) {
          this.getCursos();
          alert("Curso deletado!");
        }
      });
    }

  }

  atualizar(curso: Curso) {
    this.router.navigate(['/addCurso'], { queryParams: { curso: JSON.stringify(curso) } });
  }

  getHorario(id: number) {
    this.horarioService.getHorarioCurso(id).subscribe(
      res => {
        this.horarioService.saveHorario(res.body);
        this.router.navigate(["/listaHorarioCurso"]);
      }
    )
  }
}
