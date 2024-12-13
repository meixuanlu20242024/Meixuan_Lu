import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPolicesComponent } from './list-polices.component';

describe('ListPolicesComponent', () => {
  let component: ListPolicesComponent;
  let fixture: ComponentFixture<ListPolicesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListPolicesComponent]
    });
    fixture = TestBed.createComponent(ListPolicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
