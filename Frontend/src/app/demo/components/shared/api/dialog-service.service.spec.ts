import { TestBed } from '@angular/core/testing';

import { DialogServiceCib } from './dialog-service-cib.service';

describe('DialogServiceService', () => {
  let service: DialogServiceCib;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DialogServiceCib);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
