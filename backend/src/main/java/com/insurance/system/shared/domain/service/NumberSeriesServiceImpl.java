package com.insurance.system.shared.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.insurance.system.shared.domain.models.NumberSeries;
import com.insurance.system.shared.domain.models.NumberSeriesType;
import com.insurance.system.shared.domain.payload.NumberSeriesDTO;
import com.insurance.system.shared.domain.repository.NumberSeriesRepository;
import com.insurance.system.shared.usermanagement.payload.ApiResponse;

import java.util.Optional;

@Service
public class NumberSeriesServiceImpl implements NumberSeriesService{
    @Autowired
    private final NumberSeriesRepository numberSeriesRepository;

    public NumberSeriesServiceImpl(NumberSeriesRepository numberSeriesRepository) {
        this.numberSeriesRepository = numberSeriesRepository;
    }

    @Override
    public String generateNumber(NumberSeriesDTO numberSeriesDTO) {
        Optional<NumberSeries> optionalSeries = numberSeriesRepository.findByEntityType(numberSeriesDTO.getEntityType());
        NumberSeries numberSeries = optionalSeries.orElseGet(() -> new NumberSeries(numberSeriesDTO.getEntityType(), "1"));

        String newNumber = generateNumberWithLeadingZeros(numberSeriesDTO.getEntityType(), 7 ) ;

        numberSeries.setLastUsedNumber(newNumber);
        numberSeriesRepository.save(numberSeries);



        return newNumber ;
    }
    @Override
    public ResponseEntity<?> getLastUsedNumber(NumberSeriesDTO entityType) {
        Optional<NumberSeries> optionalSeries = numberSeriesRepository.findByEntityType(entityType.getEntityType());
        return ResponseEntity.ok().body(
                new ApiResponse(true, optionalSeries.map(NumberSeries::getLastUsedNumber).orElse("0"))
                ) ;
    }
    public String generateNumberWithLeadingZeros(NumberSeriesType entityType, int maxLength) {
        Optional<NumberSeries> optionalSeries = numberSeriesRepository.findByEntityType(entityType);
        NumberSeries numberSeries = optionalSeries.orElseGet(() -> new NumberSeries(entityType, "1"));

        String lastUsedNumberString = numberSeries.getLastUsedNumber();
//        strip  leading zeros
        lastUsedNumberString = lastUsedNumberString.replaceAll("[A-Za-z-]+", "").replaceAll("^0+", "");
//        convert to long
        Long lastUsedNumber = Long.parseLong(lastUsedNumberString);
        Long newNumber = lastUsedNumber + 1;

        if (newNumber > Math.pow(10, maxLength) - 1) {
            newNumber = 1L;
            maxLength++;
        }

        // Format the number with leading zeros
        String formattedNumber = String.format("%0" + maxLength + "d", newNumber);

        return entityType.getValue()+ formattedNumber;
    }
}
