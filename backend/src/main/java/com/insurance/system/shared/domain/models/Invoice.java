package com.insurance.system.shared.domain.models;

import com.insurance.system.shared.domain.payload.InvoiceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date paymentDate;
    private double paymentAmount;
    private String paymentMethod;
    private String paymentReference;
    private String policyType;
    private long policyId;
    private long insurerId;

    public Invoice(InvoiceDTO invoiceDTO){
        this.paymentDate =  invoiceDTO.getPaymentDate();
        this.paymentAmount = invoiceDTO.getPaymentAmount() ;
        this.paymentMethod =  invoiceDTO.getPaymentMethod() ;
        this.paymentReference = invoiceDTO.getPaymentReference() ;
        this.policyType =  invoiceDTO.getPolicyType() ;
        this.policyId = invoiceDTO.getPolicyId();
        this.insurerId =invoiceDTO.getInsurerId();
    }

    public InvoiceDTO toDTO(Insurer insurer) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(this.id);
        dto.setPaymentDate(this.paymentDate);
        dto.setPaymentAmount(this.paymentAmount);
        dto.setPaymentMethod(this.paymentMethod);
        dto.setPaymentReference(this.paymentReference);
        dto.setPolicyType(this.policyType);
        dto.setPolicyId(this.policyId);
        dto.setInsurerId(this.insurerId);
        dto.setInsurerName(insurer.getName());
        dto.setInsurerAddress(insurer.getAddress());
        return dto;
    }

}

