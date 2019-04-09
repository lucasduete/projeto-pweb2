import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Injectable } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { tap } from 'rxjs/operators';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpResponse,
} from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { InicioComponent } from './visitante/inicio/inicio.component';
import { ListaCursoComponent } from './visitante/lista-curso/lista-curso.component';
import { ListaProfessorComponent } from './visitante/lista-professor/lista-professor.component';
import { ListaAmbienteComponent } from './visitante/lista-ambiente/lista-ambiente.component';
import { LoginComponent } from './coordenador/login/login.component';
import { ProfessorComponent } from './coordenador/professor/professor.component';
import { CursoComponent } from './coordenador/curso/curso.component';
import { AmbienteComponent } from './coordenador/ambiente/ambiente.component';
import { AddAmbienteComponent } from './coordenador/add-ambiente/add-ambiente.component';
import { AddCursoComponent } from './coordenador/add-curso/add-curso.component';
import { AddProfessorComponent } from './coordenador/add-professor/add-professor.component';
import { AddCoordenadorComponent } from './coordenador/add-coordenador/add-coordenador.component';
import { CoordenadorComponent } from './coordenador/coordenador/coordenador.component';
import { ListaCoordenadorComponent } from './visitante/lista-coordenador/lista-coordenador.component';
import { InicioCoordenadorComponent } from './coordenador/inicio-coordenador/inicio-coordenador.component';
import { MenuComponent } from "./visitante/menu/menu.component";
import { HttpClientModule } from '@angular/common/http';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { MenuCoordenadorComponent } from './coordenador/menu-coordenador/menu-coordenador.component';
import { HorarioComponent } from './coordenador/horario/horario.component';
import { AddHorarioComponent } from './coordenador/add-horario/add-horario.component';
import { AddAulaComponent } from './coordenador/add-aula/add-aula.component';
import { AddDisciplinaComponent } from './coordenador/add-disciplina/add-disciplina.component';
import { ListaHorarioProfessorComponent } from './horario/lista-horario-professor/lista-horario-professor.component';
import { ListaHorarioCursoComponent } from './horario/lista-horario-curso/lista-horario-curso.component';
import { ListaHorarioAmbienteComponent } from './horario/lista-horario-ambiente/lista-horario-ambiente.component';
import { Observable } from 'rxjs';
import { VisitanteModule } from './visitante/visitante.module';



@Injectable()
export class HttpsRequestInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler, ): Observable<HttpEvent<any>> {
    console.log("interceptando requisição " + req.method.toString());

    let dupReq = req.clone();
    if (!req.url.endsWith("login")) {
       dupReq = req.clone({
        headers: req.headers.set('Authorization', this.recuperarLocalStorage())
      });
    }
    return next.handle(dupReq).pipe(
      tap(
        event => {
          if (event instanceof HttpResponse) {
            this.setarLocalStorage(event.headers.get("Authorization"));
          }
        }
      )
    );
  } 

  recuperarLocalStorage() {
    let token = localStorage.getItem("token");
    return token;
  }

  setarLocalStorage(token) {
    if (this.recuperarLocalStorage() == "null" || this.recuperarLocalStorage() == null) {
      console.log("set = " + token);
      localStorage.setItem("token", token);
    }

  }
}

@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    ListaCursoComponent,
    ListaProfessorComponent,
    ListaAmbienteComponent,
    LoginComponent,
    ProfessorComponent,
    CursoComponent,
    AmbienteComponent,
    AddAmbienteComponent,
    AddCursoComponent,
    AddProfessorComponent,
    AddCoordenadorComponent,
    CoordenadorComponent,
    ListaCoordenadorComponent,
    InicioCoordenadorComponent,
    MenuComponent,
    MenuCoordenadorComponent,
    HorarioComponent,
    AddHorarioComponent,
    AddAulaComponent,
    AddDisciplinaComponent,
    ListaHorarioProfessorComponent,
    ListaHorarioCursoComponent,
    ListaHorarioAmbienteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgbModalModule,
    HttpClientModule,
    VisitanteModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpsRequestInterceptor,
    multi: true,
  },],
  bootstrap: [AppComponent]
})
export class AppModule { }
