import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCoordenadorComponent } from './add-coordenador.component';

describe('AddCoordenadorComponent', () => {
  let component: AddCoordenadorComponent;
  let fixture: ComponentFixture<AddCoordenadorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCoordenadorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCoordenadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
