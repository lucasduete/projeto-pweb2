import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaHorarioAmbienteComponent } from './lista-horario-ambiente.component';

describe('ListaHorarioAmbienteComponent', () => {
  let component: ListaHorarioAmbienteComponent;
  let fixture: ComponentFixture<ListaHorarioAmbienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaHorarioAmbienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaHorarioAmbienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
