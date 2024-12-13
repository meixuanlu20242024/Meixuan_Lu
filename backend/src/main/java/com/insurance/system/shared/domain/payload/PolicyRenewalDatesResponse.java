package com.insurance.system.shared.domain.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyRenewalDatesResponse {
    Long expired ;
    Long due ;
    long dueIn7days ;
    Long dueIn30Days ;
    Long dueIn60Days ;
    Long dueIn90Days ;
    Long dueIn90PlusDays ;

    public PolicyRenewalDatesResponse(long expired, long due , long dueIn7days  , long countThirty, long countSixty, long countNinety, long count90plus) {
        this.expired = expired;
        this.due = due;
        this.dueIn7days = dueIn7days;
        this.dueIn30Days = countThirty;
        this.dueIn60Days = countSixty;
        this.dueIn90Days = countNinety;
        this.dueIn90PlusDays = count90plus;
    }
}
