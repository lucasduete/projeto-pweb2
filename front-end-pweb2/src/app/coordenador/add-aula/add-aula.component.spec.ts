import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAulaComponent } from './add-aula.component';

describe('AddAulaComponent', () => {
  let component: AddAulaComponent;
  let fixture: ComponentFixture<AddAulaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAulaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAulaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
