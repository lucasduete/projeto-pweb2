import { TestBed } from '@angular/core/testing';

import { CoordenadorService } from './coordenador.service';

describe('CoordenadorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CoordenadorService = TestBed.get(CoordenadorService);
    expect(service).toBeTruthy();
  });
});
