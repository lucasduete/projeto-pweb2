import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InicioComponent } from './visitante/inicio/inicio.component';
import { ListaCursoComponent } from './visitante/lista-curso/lista-curso.component';
import { ListaProfessorComponent } from './visitante/lista-professor/lista-professor.component';
import { ListaCoordenadorComponent } from './visitante/lista-coordenador/lista-coordenador.component';
import { ListaAmbienteComponent } from './visitante/lista-ambiente/lista-ambiente.component';
import { LoginComponent } from './coordenador/login/login.component';
import { InicioCoordenadorComponent } from './coordenador/inicio-coordenador/inicio-coordenador.component';
import { CursoComponent } from './coordenador/curso/curso.component';
import { AmbienteComponent } from './coordenador/ambiente/ambiente.component';
import { AddAmbienteComponent } from './coordenador/add-ambiente/add-ambiente.component';
import { AddCursoComponent } from './coordenador/add-curso/add-curso.component';
import { AddProfessorComponent } from './coordenador/add-professor/add-professor.component';
import { AddCoordenadorComponent } from './coordenador/add-coordenador/add-coordenador.component';
import { CoordenadorComponent } from './coordenador/coordenador/coordenador.component';
import { ProfessorComponent } from './coordenador/professor/professor.component';
import { HorarioComponent } from './coordenador/horario/horario.component';
import { AddHorarioComponent } from './coordenador/add-horario/add-horario.component';
import { AddAulaComponent } from './coordenador/add-aula/add-aula.component';
import { AddDisciplinaComponent } from './coordenador/add-disciplina/add-disciplina.component'
import { ListaHorarioProfessorComponent } from './horario/lista-horario-professor/lista-horario-professor.component';
import { ListaHorarioCursoComponent } from './horario/lista-horario-curso/lista-horario-curso.component';
import { ListaHorarioAmbienteComponent } from './horario/lista-horario-ambiente/lista-horario-ambiente.component';

const routes: Routes = [
  { path: '', component: InicioComponent},
  { path: 'listaCurso', component: ListaCursoComponent},
  { path: 'listaProfessor', component: ListaProfessorComponent},
  { path: 'listaAmbiente', component: ListaAmbienteComponent},
  { path: 'listaCoordenador', component: ListaCoordenadorComponent},
  { path: 'login', component: LoginComponent},
  { path: 'curso', component: CursoComponent},
  { path: 'inicioCoordenador', component: InicioCoordenadorComponent},
  { path: 'coordenador', component: CoordenadorComponent},
  { path: 'ambiente', component: AmbienteComponent},
  { path: 'professor', component: ProfessorComponent},
  { path: 'addCoordenador', component: AddCoordenadorComponent},
  { path: 'addAmbiente', component: AddAmbienteComponent},
  { path: 'addCurso', component: AddCursoComponent},
  { path: 'addProfessor', component: AddProfessorComponent},
  { path: 'horario', component: HorarioComponent},
  { path: 'addDias', component: AddHorarioComponent},
  { path: 'addAula', component: AddAulaComponent},
  { path: 'addDisciplina', component: AddDisciplinaComponent},
  { path: 'listaHorarioProfessor', component: ListaHorarioProfessorComponent},
  { path: 'listaHorarioCurso', component: ListaHorarioCursoComponent},
  { path: 'listaHorarioAmbiente', component: ListaHorarioAmbienteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }