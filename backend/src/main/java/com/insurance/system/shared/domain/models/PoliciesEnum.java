package com.insurance.system.shared.domain.models;



public enum PoliciesEnum {
    MotorPolicy("Motor Policy"),
    PublicLiability("Public Liability"),
    UmbrellaLiability("Umbrella Liability"),
    ProductLiability("Product Liability"),
    PlantAllRisk("Plant All Risk"),
    CarrierLiability("Carrier Liability"),
    TobaccoThroughput("Tobacco Throughput"),
    DirectorsAndOfficers("Directors and Officers"),
    EmployersLiability("Employers Liability"),
    ElectronicEquipment("Electronic Equipment"),
    AssetAllRisk("Asset All Risk"),
    LoanProtection("Loan Protection"),
    MachineryBreakdown("Machinery Breakdown"),
    HomePolicy("Home Policy"),
    GroupPersonalAccident("Group Personal Accident"),
    DirectorsAndOfficersLiability("Directors and Officers Liability"),
    WarehousemanLiability("Warehouseman Liability"),
    MachineryBreakdownLossOfProfits("Machinery Breakdown Loss Of Profits"),
    Money("Money"),
    DirectorsPersonalAccident("Directors Personal Accident"),
    ProfessionalIndemnity("Professional Indemnity"),
    Travel("Travel"),
    PoliticalViolence("Political Violence"),
    MotorExcessBuyBack("Motor Excess Buy Back"),
    BondsAndGuarantees("Bonds And Guarantees"),
    WarehouseLiability("WarehouseLiability") ,//to be deleted
    GroupPersonal("Group Personal") ; //to be deleted




    private String policyName;

    PoliciesEnum(String policyName) {
        this.policyName = policyName;
    }

}
