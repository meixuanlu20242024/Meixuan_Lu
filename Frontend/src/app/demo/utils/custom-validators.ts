import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function dateRangeValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
        const periodFromControl = control.get('periodFrom');
        const periodToControl = control.get('periodTo');

        if (periodFromControl && periodToControl) {
            const periodFrom = periodFromControl.value;
            const periodTo = periodToControl.value;

            if (periodFrom && periodTo && periodTo < periodFrom) {
                return { dateRangeError: true };
            }
        }

        return null;
    };
}
