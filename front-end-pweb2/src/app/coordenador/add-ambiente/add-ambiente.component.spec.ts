import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAmbienteComponent } from './add-ambiente.component';

describe('AddAmbienteComponent', () => {
  let component: AddAmbienteComponent;
  let fixture: ComponentFixture<AddAmbienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAmbienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAmbienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
