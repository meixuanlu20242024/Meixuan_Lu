package com.insurance.system.shared.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NumberSeriesType entityType;

    private String lastUsedNumber;

    public NumberSeries(NumberSeriesType entityType, String l) {
        this.entityType = entityType;
        this.lastUsedNumber = l;
    }
}
