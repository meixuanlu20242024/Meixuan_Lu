package com.insurance.system.shared.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.insurance.system.shared.domain.models.NumberSeriesType;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class NumberSeriesDTO {
    NumberSeriesType entityType;
}
