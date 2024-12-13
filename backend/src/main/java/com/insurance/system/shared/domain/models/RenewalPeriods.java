package com.insurance.system.shared.domain.models;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum RenewalPeriods {
    DUE("due"),
    DUEIN7DAYS("dueIn7Days"),
    DUEIN30DAYS("dueIn30Days"),
    DUEIN60DAYS("dueIn60Days"),
    DUEIN90DAYS("dueIn90Days"),
    DUEIN90PLUSDAYS("dueIn90PlusDays"),
    EXPIRED("expired");

    private String renewalPeriod;

    RenewalPeriods(String renewalPeriod) {
        this.renewalPeriod = renewalPeriod;
    }

    public String getRenewalPeriod() {


        return this.renewalPeriod;
    }

//    get number of days for each renewal period
    public long getDays() {
        switch (this) {
            case DUE:
                return 0;
            case DUEIN7DAYS:
                return 7;
            case DUEIN30DAYS:
                return 30;
            case DUEIN60DAYS:
                return 60;
            case DUEIN90DAYS:
                return 90;
            case DUEIN90PLUSDAYS:
                return 91;
            case EXPIRED:
                return 0;
            default:
                return 0;
        }
    }
}
