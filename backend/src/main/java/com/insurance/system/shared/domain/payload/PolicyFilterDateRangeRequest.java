package com.insurance.system.shared.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PolicyFilterDateRangeRequest {
    public int month;
    public int year;
    public int quarter;
}
