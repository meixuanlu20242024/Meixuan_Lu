package com.insurance.system.shared.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RenewalsToDates {

    private  Date today ;
    private  Date sevenDays ;
    private  Date thirtyDaysFromToday ;
    private  Date sixtyDaysFromToday ;
    private  Date ninetyDaysFromToday ;

}
