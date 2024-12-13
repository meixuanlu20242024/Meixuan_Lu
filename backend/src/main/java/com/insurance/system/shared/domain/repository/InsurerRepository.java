package com.insurance.system.shared.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.insurance.system.shared.domain.models.Insurer;

import java.util.List;
import java.util.Optional;

@Repository
public interface InsurerRepository extends JpaRepository<Insurer, Long> {
  Optional<Insurer> findByName(String paramString);

    Page<Insurer> findAll(Specification<Insurer> filteredClients, Pageable pageable);

    boolean existsByEmail(String email);




  @Query(value = "" +

//          MotorPolicy("Motor Policy"),
//          PublicLiability("Public Liability"),
//          UmbrellaLiability("Umbrella Liability"),
//          ProductLiability("Product Liability"),
//          PlantAllRisk("Plant All Risk"),
//          CarrierLiability("Carrier Liability"),
//          WarehouseLiability("Warehouse Liability"),
//          TobaccoThroughput("Tobacco Throughput"),
//          DirectorsAndOfficers("Directors and Officers"),
//          EmployersLiability("Employers Liability"),
//          GroupPersonal("Group Personal"),
//          ElectronicEquipment("Electronic Equipment"),
//          AssetAllRisk("Asset All Risk"),
//          LoanProtection("Loan Protection"),
//          MachineryBreakdown("Machinery Breakdown"),
//          HomePolicy("Home Policy"),
//          GroupPersonalAccident("Group Personal Accident"),
//          DirectorsAndOfficersLiability("Directors and Officers Liability"),
//          WarehousemanLiability("Warehouseman Liability"),
//          MachineryBreakdownLossOfProfits("Machinery Breakdown Loss Of Profits"),
//          Money("Money"),
//          DirectorsPersonalAccident("Directors Personal Accident"),
//          ProfessionalIndemnity("Professional Indemnity"),
//          Travel("Travel"),
//          PoliticalViolence("Political Violence"),
//          MotorExcessBuyBack("Motor Excess Buy Back"),
//          BondsAndGuarantees("Bonds And Guarantees");


          "SELECT 'MotorPolicy' as policyType, SUM(m.sumInsured) as totalAmount ,  :currency as currency  , SUM(m.governmentLevy) as totalgvtLevy , SUM(m.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable " +
          "FROM MotorPolicy m LEFT JOIN Invoice i ON m.id = i.policyId " +
          "WHERE m.insurer_id = :insurerId AND m.currency = :currency " +
          "AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo " +
          "AND i.policyType = 'MOTORPOLICY' " +
          "UNION "+

            "SELECT 'PublicLiability' as policyType, SUM(p.sumInsured) as totalAmount ,  :currency as currency  , SUM(p.governmentLevy) as totalgvtLevy , SUM(p.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM PublicLiabilityPolicy p LEFT JOIN Invoice i ON p.id = i.policyId " +
            "WHERE p.insurer_id = :insurerId AND p.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'PUBLICLIABILITY' " +
          "UNION "+
            "SELECT 'UmbrellaLiability' as policyType, SUM(u.sumInsured) as totalAmount ,  :currency as currency  , SUM(u.governmentLevy) as totalgvtLevy , SUM(u.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM UmbrellaLiabilityPolicy u LEFT JOIN Invoice i ON u.id = i.policyId " +
            "WHERE u.insurer_id = :insurerId AND u.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'UMBRELLALIABILITYPOLICY' " +
          "UNION "+
            "SELECT 'ProductLiability' as policyType, SUM(v.sumInsured) as totalAmount ,  :currency as currency  , SUM(v.governmentLevy) as totalgvtLevy , SUM(v.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM ProductLiabilityPolicy v LEFT JOIN Invoice i ON v.id = i.policyId " +
            "WHERE v.insurer_id = :insurerId AND v.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'PRODUCTLIABILITY' " +
          "UNION "+
//            "SELECT 'PlantAllRisk' as policyType, SUM(x.sumInsured) as totalAmount ,  :currency as currency  , SUM(x.governmentLevy) as totalgvtLevy , SUM(x.stampDuty) as totalstampDuty , SUM(x.paymentAmount) as totalAmountPayable  " +
//            "FROM plantallriskpolicy x " +
//            "WHERE x.insurer_id = :insurerId AND x.currency = :currency "+
//            "AND i.policyType = 'MOTORPOLICY' " +
         // "UNION "+
            "SELECT 'CarrierLiability' as policyType, SUM(c.sumInsured) as totalAmount ,  :currency as currency  , SUM(c.governmentLevy) as totalgvtLevy , SUM(c.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM CarriersLiabilityPolicy c LEFT JOIN Invoice i ON c.id = i.policyId " +
            "WHERE c.insurer_id = :insurerId AND c.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'CARRIERSLIABILITY' " +
          "UNION "+
            "SELECT 'WarehousemanLiability' as policyType, SUM(w.sumInsured) as totalAmount ,  :currency as currency  , SUM(w.governmentLevy) as totalgvtLevy , SUM(w.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM WarehouseLiabilityPolicy w LEFT JOIN Invoice i ON w.id = i.policyId " +
            "WHERE w.insurer_id = :insurerId AND w.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'WAREHOUSEMANLIABILITY' " +
          "UNION "+
            "SELECT 'TobaccoThroughput' as policyType, SUM(t.sumInsured) as totalAmount ,  :currency as currency  , SUM(t.governmentLevy) as totalgvtLevy , SUM(t.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM TobaccoThroughputPolicy t LEFT JOIN Invoice i ON t.id = i.policyId " +
            "WHERE t.insurer_id = :insurerId AND t.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'TOBACCOTHROUGHPUT' " +
          "UNION "+
            "SELECT 'EmployersLiability' as policyType, SUM(e.sumInsured) as totalAmount ,  :currency as currency  , SUM(e.governmentLevy) as totalgvtLevy , SUM(e.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM EmployersLiabilityPolicy e LEFT JOIN Invoice i ON e.id = i.policyId " +
            "WHERE e.insurer_id = :insurerId AND e.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'EMPLOYERSLIABILITY' " +
          "UNION "+
            "SELECT 'ElectronicEquipment' as policyType, SUM(e.sumInsured) as totalAmount ,  :currency as currency  , SUM(e.governmentLevy) as totalgvtLevy , SUM(e.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM ElectronicEquipmentPolicy e LEFT JOIN Invoice i ON e.id = i.policyId " +
            "WHERE e.insurer_id = :insurerId AND e.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'ELECTRONICEQUIPMENT' " +
          "UNION "+
            "SELECT 'AssetAllRisk' as policyType, SUM(a.sumInsured) as totalAmount ,  :currency as currency  , SUM(a.governmentLevy) as totalgvtLevy , SUM(a.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM AssetAllRiskPolicy a LEFT JOIN Invoice i ON a.id = i.policyId " +
            "WHERE a.insurer_id = :insurerId AND a.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'ASSETALLRISK' " +
          "UNION "+
            "SELECT 'LoanProtection' as policyType, SUM(l.sumInsured) as totalAmount ,  :currency as currency  , SUM(l.governmentLevy) as totalgvtLevy , SUM(l.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM LoanProtectionPolicy l LEFT JOIN Invoice i ON l.id = i.policyId " +
            "WHERE l.insurer_id = :insurerId AND l.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'LOANPROTECTION' " +
          "UNION "+
            "SELECT 'MachineryBreakdown' as policyType, SUM(m.sumInsured) as totalAmount ,  :currency as currency  , SUM(m.governmentLevy) as totalgvtLevy , SUM(m.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM MachineryBreakdownPolicy m LEFT JOIN Invoice i ON m.id = i.policyId " +
            "WHERE m.insurer_id = :insurerId AND m.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'MACHINERYBREAKDOWN' " +
          "UNION "+
            "SELECT 'HomePolicy' as policyType, SUM(h.sumInsured) as totalAmount ,  :currency as currency  , SUM(h.governmentLevy) as totalgvtLevy , SUM(h.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM HomePolicy h LEFT JOIN Invoice i ON h.id = i.policyId " +
            "WHERE h.insurer_id = :insurerId AND h.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'HOMEPOLICY' " +
          "UNION "+
            "SELECT 'GroupPersonalAccident' as policyType, SUM(g.sumInsured) as totalAmount ,  :currency as currency  , SUM(g.governmentLevy) as totalgvtLevy , SUM(g.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM GroupPersonalAccidentPolicy g LEFT JOIN Invoice i ON g.id = i.policyId " +
            "WHERE g.insurer_id = :insurerId AND g.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'GROUPPERSONALACCIDENT' " +
          "UNION "+
            "SELECT 'DirectorsAndOfficersLiability' as policyType, SUM(d.sumInsured) as totalAmount ,  :currency as currency  , SUM(d.governmentLevy) as totalgvtLevy , SUM(d.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM DirectorOfficePolicy d LEFT JOIN Invoice i ON d.id = i.policyId " +
            "WHERE d.insurer_id = :insurerId AND d.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'DIRECTORSANDOFFICERSLIABILITY' " +
          "UNION "+
            "SELECT 'MachineryBreakdownLossOfProfits' as policyType, SUM(m.sumInsured) as totalAmount ,  :currency as currency  , SUM(m.governmentLevy) as totalgvtLevy , SUM(m.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM MBreakdownLOPPolicy m LEFT JOIN Invoice i ON m.id = i.policyId " +
            "WHERE m.insurer_id = :insurerId AND m.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'MACHINERYBREAKDOWNLOSSOFPROFITS' " +
          "UNION "+
            "SELECT 'Money' as policyType, SUM(m.sumInsured) as totalAmount ,  :currency as currency  , SUM(m.governmentLevy) as totalgvtLevy , SUM(m.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM MoneyPolicy m LEFT JOIN Invoice i ON m.id = i.policyId " +
            "WHERE m.insurer_id = :insurerId AND m.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'MONEY' " +
          "UNION "+
            "SELECT 'ProfessionalIndemnity' as policyType, SUM(y.sumInsured) as totalAmount ,  :currency as currency  , SUM(y.governmentLevy) as totalgvtLevy , SUM(y.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM ProfessionalIndemnityPolicy y LEFT JOIN Invoice i ON y.id = i.policyId " +
            "WHERE y.insurer_id = :insurerId AND y.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'PROFESSIONALINDEMNITY' " +
          "UNION "+
            "SELECT 'Travel' as policyType, SUM(t.sumInsured) as totalAmount ,  :currency as currency , SUM(t.governmentLevy) as totalgvtLevy , SUM(t.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM TravelPolicy t LEFT JOIN Invoice i ON t.id = i.policyId " +
            "WHERE t.insurer_id = :insurerId AND t.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'TRAVEL' " +
          "UNION "+
            "SELECT 'PoliticalViolence' as policyType, SUM(z.sumInsured) as totalAmount ,  :currency as currency  , SUM(z.governmentLevy) as totalgvtLevy , SUM(z.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM PoliticalViolencePolicy z LEFT JOIN Invoice i ON z.id = i.policyId " +
            "WHERE z.insurer_id = :insurerId AND z.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'POLITICALVIOLENCE' " +
          "UNION "+
            "SELECT 'MotorExcessBuyBack' as policyType, SUM(m.sumInsured) as totalAmount ,  :currency as currency  , SUM(m.governmentLevy) as totalgvtLevy , SUM(m.stampDuty) as totalstampDuty , SUM(i.paymentAmount) as totalAmountPayable  " +
            "FROM MotorExcessBuyBackPolicy m LEFT JOIN Invoice i ON m.id = i.policyId " +
            "WHERE m.insurer_id = :insurerId AND m.currency = :currency AND  i.paymentDate >= :periodFrom AND i.paymentDate <= :periodTo "+
            "AND i.policyType = 'MOTOREXCESSBUYBACK' " ,
//            "SELECT 'BONDSANDGUARANTEES' as policyType, SUM(b.sumInsured) as totalAmount ,  :currency as currency  " +
//            "FROM bondsandguaranteespolicy b " +
//            "WHERE b.insurer_id = :insurerId "
            nativeQuery = true)


  List<Object[]>  getAllPolicies(Long insurerId,
                                 String currency,
                                 String periodFrom,
                                 String periodTo );



}
