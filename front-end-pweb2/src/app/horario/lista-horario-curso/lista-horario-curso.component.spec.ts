import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaHorarioCursoComponent } from './lista-horario-curso.component';

describe('ListaHorarioCursoComponent', () => {
  let component: ListaHorarioCursoComponent;
  let fixture: ComponentFixture<ListaHorarioCursoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaHorarioCursoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaHorarioCursoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
