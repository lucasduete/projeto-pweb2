import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InicioCoordenadorComponent } from './inicio-coordenador.component';

describe('InicioCoordenadorComponent', () => {
  let component: InicioCoordenadorComponent;
  let fixture: ComponentFixture<InicioCoordenadorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InicioCoordenadorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InicioCoordenadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
