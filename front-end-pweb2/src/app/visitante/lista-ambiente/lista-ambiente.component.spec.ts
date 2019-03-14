import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaAmbienteComponent } from './lista-ambiente.component';

describe('ListaAmbienteComponent', () => {
  let component: ListaAmbienteComponent;
  let fixture: ComponentFixture<ListaAmbienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaAmbienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaAmbienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
