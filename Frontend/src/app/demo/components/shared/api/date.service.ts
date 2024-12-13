import { Injectable } from '@angular/core';
import {ValueLabel} from "./shared.models";

@Injectable({
    providedIn: 'root',
})
export class DateService {
    generateYears(startYear: number = 1970, endYear: number = 2050): string[] {
        const years: string[] = [];
        for (let year = startYear; year <= endYear; year++) {
            years.push(year.toString());
        }
        return years;
    }
    getMonths(): ValueLabel[] {
        return [
            { value: 1, label: 'January' },
            { value: 2, label: 'February' },
            { value: 3, label: 'March' },
            { value: 4, label: 'April' },
            { value: 5, label: 'May' },
            { value: 6, label: 'June' },
            { value: 7, label: 'July' },
            { value: 8, label: 'August' },
            { value: 9, label: 'September' },
            { value: 10, label: 'October' },
            { value: 11, label: 'November' },
            { value: 12, label: 'December' },
        ];
    }
    getRanges(): ValueLabel[] {
        return [
            { value: 1, label: 'Monthly' },
            { value: 2, label: 'First Quarter' },
            { value: 3, label: 'Second Quarter' },
            { value: 4, label: 'Third Quarter' },
            { value: 5, label: 'Fourth Quarter' },
        ];
    }
}
