import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AmbienteComponent } from './ambiente.component';

describe('AmbienteComponent', () => {
  let component: AmbienteComponent;
  let fixture: ComponentFixture<AmbienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AmbienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AmbienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
