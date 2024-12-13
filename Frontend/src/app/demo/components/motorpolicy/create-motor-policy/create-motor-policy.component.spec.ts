import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMotorPolicyComponent } from './create-motor-policy.component';

describe('CreateMotorPolicyComponent', () => {
  let component: CreateMotorPolicyComponent;
  let fixture: ComponentFixture<CreateMotorPolicyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateMotorPolicyComponent]
    });
    fixture = TestBed.createComponent(CreateMotorPolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
