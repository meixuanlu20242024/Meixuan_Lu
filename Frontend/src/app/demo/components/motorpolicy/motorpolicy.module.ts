import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MotorpolicyRoutingModule } from './motorpolicy-routing.module';
import { CreateMotorPolicyComponent } from './create-motor-policy/create-motor-policy.component';
import { ListPolicesComponent } from './list-polices/list-polices.component';


@NgModule({
  declarations: [
    CreateMotorPolicyComponent,
    ListPolicesComponent
  ],
  imports: [
    CommonModule,
    MotorpolicyRoutingModule
  ]
})
export class MotorpolicyModule { }
