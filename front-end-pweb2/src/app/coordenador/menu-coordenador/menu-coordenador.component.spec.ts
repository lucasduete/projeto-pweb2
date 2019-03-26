import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuCoordenadorComponent } from './menu-coordenador.component';

describe('MenuCoordenadorComponent', () => {
  let component: MenuCoordenadorComponent;
  let fixture: ComponentFixture<MenuCoordenadorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuCoordenadorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuCoordenadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
