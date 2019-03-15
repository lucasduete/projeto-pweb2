import { TestBed } from '@angular/core/testing';

import { AmbienteService } from './ambiente.service';

describe('AmbienteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AmbienteService = TestBed.get(AmbienteService);
    expect(service).toBeTruthy();
  });
});
