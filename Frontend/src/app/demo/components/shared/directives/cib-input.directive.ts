import {Directive, ElementRef, HostBinding, HostListener} from '@angular/core';

@Directive({
  selector: '[appCibInputDirective]'
})
export class CibInputDirective {

    constructor(private el: ElementRef) {}

    @HostBinding('class.p-inputwrapper-filled')
    get isFilled() {
        return this.el.nativeElement.classList.contains('ng-valid');
    }

    @HostBinding('class.p-inputwrapper-focus')
    get isFocused() {
        return this.el.nativeElement.classList.contains('ng-dirty');
    }

    @HostBinding('class.p-inputwrapper-error')
    get isError() {
        return this.el.nativeElement.classList.contains('ng-invalid') && this.el.nativeElement.classList.contains('ng-dirty');
    }

}
