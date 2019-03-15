import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaCoordenadorComponent } from './lista-coordenador.component';

describe('ListaCoordenadorComponent', () => {
  let component: ListaCoordenadorComponent;
  let fixture: ComponentFixture<ListaCoordenadorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaCoordenadorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaCoordenadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
