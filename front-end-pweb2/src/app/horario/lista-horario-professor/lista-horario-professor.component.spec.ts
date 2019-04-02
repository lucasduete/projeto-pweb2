import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaHorarioProfessorComponent } from './lista-horario-professor.component';

describe('ListaHorarioProfessorComponent', () => {
  let component: ListaHorarioProfessorComponent;
  let fixture: ComponentFixture<ListaHorarioProfessorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaHorarioProfessorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaHorarioProfessorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
