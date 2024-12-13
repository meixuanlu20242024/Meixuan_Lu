package com.insurance.system.motorpolicy.domain.service;

import com.google.gson.Gson;
import com.insurance.system.motorpolicy.domain.models.MotorPolicy;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyDocument;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyEnumDocumentType;
import com.insurance.system.motorpolicy.domain.models.MotorPolicyVehicle;
import com.insurance.system.motorpolicy.domain.models.renewal.MotorPolicyRenewal;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyRenewalRepository;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyRepository;
import com.insurance.system.motorpolicy.domain.repository.MotorPolicyVehicleRepository;
import com.insurance.system.motorpolicy.payload.MotorPolicyRequest;
import com.insurance.system.shared.domain.filters.PolicyFilterSpecification;
import com.insurance.system.shared.domain.models.ClientRenewalNotification;
import com.insurance.system.shared.domain.models.PoliciesEnum;
import com.insurance.system.shared.domain.models.RenewalPeriods;
import com.insurance.system.shared.domain.payload.*;
import com.insurance.system.shared.domain.repository.ClientRenewalNotificationRepository;
import com.insurance.system.shared.domain.service.NumberSeriesService;
import com.insurance.system.shared.domain.service.PolicyService;
import com.insurance.system.shared.domain.service.PolicyServiceImpl;
import com.insurance.system.shared.filestorage.context.File;
import com.insurance.system.shared.filestorage.context.FileDao;
import com.insurance.system.shared.filestorage.context.FileDto;
import com.insurance.system.shared.filestorage.context.delete.FileDeleteRequest;
import com.insurance.system.shared.filestorage.context.delete.FileDeleteService;
import com.insurance.system.shared.filestorage.context.upload.FileUploadRequest;
import com.insurance.system.shared.filestorage.context.upload.FileUploadService;
import com.insurance.system.shared.usermanagement.payload.ApiResponse;
import com.insurance.system.shared.utils.CustomBeanUtils;
import com.insurance.system.shared.utils.Utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class MotorPolicyServiceImpl implements MotorPolicyService {
  private static final Logger log = LoggerFactory.getLogger(MotorPolicyServiceImpl.class);

  private final FileUploadService fileUploadService;
  private final ClientRenewalNotificationRepository clientRenewalNotificationRepository;

  private final MotorPolicyRepository motorPolicyRepository;
  private final MotorPolicyRenewalRepository motorPolicyRenewalRepository;
  private final MotorPolicyVehicleRepository motorPolicyVehicleRepository;
  private final NumberSeriesService numberSeriesService ;

  private final FileDeleteService fileDeleteService;

  private final FileDao fileDao;

  private final PolicyService policyService;
  private final PolicyServiceImpl.EmailService emailService;



  @Autowired
  public MotorPolicyServiceImpl(FileUploadService fileUploadService, ClientRenewalNotificationRepository clientRenewalNotificationRepository, MotorPolicyRepository motorPolicyRepository, MotorPolicyRenewalRepository motorPolicyRenewalRepository, MotorPolicyVehicleRepository motorPolicyVehicleRepository, NumberSeriesService numberSeriesService, FileDeleteService fileDeleteService, FileDao fileDao, PolicyService policyService, PolicyServiceImpl.EmailService emailService) {
    this.fileUploadService = fileUploadService;
    this.clientRenewalNotificationRepository = clientRenewalNotificationRepository;
    this.motorPolicyRepository = motorPolicyRepository;
    this.motorPolicyRenewalRepository = motorPolicyRenewalRepository;
    this.motorPolicyVehicleRepository = motorPolicyVehicleRepository;
    this.numberSeriesService = numberSeriesService;
    this.fileDeleteService = fileDeleteService;
    this.fileDao = fileDao;
    this.policyService = policyService;
    this.emailService = emailService;
  }

  @Deprecated


  public boolean createPolicy(MotorPolicy policy, MultipartFile[] files, MultipartFile policyExcel) throws Exception {
    List<MotorPolicyVehicle> motorPolicyVehicles = new ArrayList<>();
//    ===start excell file processing

    log.info("Processing excel file {} " , policyExcel.getInputStream());


    if (!policyExcel.isEmpty()) {

       Workbook workbook = new XSSFWorkbook(policyExcel.getInputStream());
      Sheet sheet = workbook.getSheetAt(0);
      Map<Integer, List<String>> data = new HashMap<>();
      int i = 1;
      for (Row row : sheet) {
        data.put(i, new ArrayList<String>());
        for (Cell cell : row) {
          switch (cell.getCellType()) {
            case STRING:
              data.get(Integer.valueOf(i)).add(cell.getRichStringCellValue().getString());
              break;
            case NUMERIC:
              if (DateUtil.isCellDateFormatted(cell)) {
                data.get(i).add(cell.getDateCellValue() + "");
              } else {
                data.get(i).add(cell.getNumericCellValue() + "");
              }
              break;
            case BOOLEAN:
              data.get(i).add(cell.getBooleanCellValue() + "");
              break;
            case FORMULA:
              data.get(i).add(cell.getCellFormula() + "");
              break;
            default: data.get(Integer.valueOf(i)).add(" ");
          }
        }
        i++;
      }

      log.info("data______________________________________: {}", data);
//       map  data to allassetrisk2 vehicle
        for (Map.Entry<Integer, List<String>> entry : data.entrySet()) {
//          ignore header
            if (entry.getKey() == 1) {
                continue;
            }
          MotorPolicyVehicle motorPolicyVehicle = new MotorPolicyVehicle();
          motorPolicyVehicle.setName(entry.getValue().get(0));
          motorPolicyVehicle.setMake(entry.getValue().get(1));
          motorPolicyVehicle.setYear(entry.getValue().get(2));
          motorPolicyVehicle.setRegistrationNumber(entry.getValue().get(3));
//          make pricde alwas positive
            motorPolicyVehicle.setPrice(Math.abs(Double.parseDouble(entry.getValue().get(4))));
          motorPolicyVehicle.setPolicy(policy);
          motorPolicyVehicles.add(motorPolicyVehicle);
        }

    }
    else if(!policy.getTmpVehicles().equals("")){

      log.info("tmpVehicles: " + policy.getTmpVehicles());

//      start local upload

      Gson gson = new Gson();

      List gsonList = gson.fromJson(policy.getTmpVehicles(), List.class);


      for (Object o : gsonList) {
        MotorPolicyVehicle vehicle = new MotorPolicyVehicle();
        Map map = (Map) o;
        vehicle.setMake(map.get("make").toString());
        vehicle.setModel(map.get("model").toString());
        vehicle.setYear(map.get("vehicle_year").toString());
//        vehicle.setYear(map.get("year").toString());//vehicle_year
        vehicle.setRegistrationNumber(map.get("registrationNumber").toString());
        vehicle.setName(map.get("name").toString());
        vehicle.setPolicy(policy);
        log.info("Creating policy {} ", map.get("make").toString());
        motorPolicyVehicles.add(vehicle);

      }


      policy.setTmpVehicles("blank");

    }


    List<MotorPolicyDocument> motorPolicyDocuments = new ArrayList<>();
//    check files is empty
    log.info("files {} ", files.length);
    if (files.length > 0) {
      for (MultipartFile file : files) {
        FileUploadRequest fileUploadRequest = FileUploadRequest.createFileUploadRequest(file, "allassetrisk2/parent");
        File savedFile = this.fileUploadService.uploadFile(fileUploadRequest);
        String filename = FileDto.createFileDto(savedFile).getFileName();
        MotorPolicyDocument motorPolicyDocument = new MotorPolicyDocument();
        motorPolicyDocument.setFileName(filename);
        motorPolicyDocument.setOriginalName(file.getOriginalFilename());
        motorPolicyDocument.setMotorPolicyEnumDocumentType(MotorPolicyEnumDocumentType.PARENT);
        motorPolicyDocument.setPolicy(policy);
        motorPolicyDocuments.add(motorPolicyDocument);
      }
    }
    policy.setMotorPolicyDocuments(motorPolicyDocuments);
    policy.setMotorVehicles(motorPolicyVehicles);
    policy.setCreatedAt(new Date());
    policy.setCreatedBy("admin");
//    Double premium = this.policyService.calculatePremium(policy.getPeriodFrom(), policy.getPeriodTo(), policy.getSumInsured(), policy.getRate());
//    policy.setPremium(premium);
//    Double stampDuty = this.policyService.calculateStampDuty(premium);
//    policy.setStampDuty(stampDuty);
//    policy.setGovernmentLevy(this.policyService.calculateGvtLevy(premium, stampDuty));


    MotorPolicy saved = motorPolicyRepository.save(policy);
    if (saved != null) {

      return true;
    }
    return false;
  }

  public String updatePolicy(Long id, MotorPolicy policy) throws Exception {




    Optional<MotorPolicy> existingPolicy = this.motorPolicyRepository.findById(id);
//    Double premium = this.policyService.calculatePremium(policy.getPeriodFrom(), policy.getPeriodTo(), policy.getSumInsured(), policy.getRate());
//    policy.setPremium(premium);
//    Double stampDuty = this.policyService.calculateStampDuty(premium);
//    policy.setStampDuty(stampDuty);
//    delete policy.getMotorVehicles()


    List<MotorPolicyVehicle> existingVehicles = existingPolicy.get().getMotorVehicles();
    BeanUtils.copyProperties(policy, existingPolicy.get(), CustomBeanUtils.getNullPropertyNames(policy));

    Gson gson = new Gson();
    List gsonList = gson.fromJson(policy.getTmpVehicles(), List.class);
    log.info(" gsonList vehicles: " + gsonList);
    List<MotorPolicyVehicle> motorPolicyVehicles = new ArrayList<>();
    for (Object o : gsonList) {
      MotorPolicyVehicle vehicle = new MotorPolicyVehicle();

      Map map = (Map) o;
      vehicle.setMake(map.get("make").toString());
      vehicle.setModel(map.get("model").toString());
      vehicle.setYear(map.get("vehicle_year").toString());
//      vehicle.setYear(map.get("year").toString());//vehicle_year
      vehicle.setRegistrationNumber(map.get("registrationNumber").toString());
      vehicle.setName(map.get("name").toString());
      vehicle.setPolicy(policy);
      log.info("Creating policy {} ", map.get("make").toString());
      motorPolicyVehicles.add(vehicle);

    }
        motorPolicyVehicleRepository.deleteAllInBatch(existingVehicles);
    existingPolicy.get().setMotorVehicles(motorPolicyVehicles);
    existingPolicy.get().setTmpVehicles("blank");
    this.motorPolicyRepository.save(existingPolicy.get());
    return null;
  }

  public Page<MotorPolicy> PoliciesPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
    Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(new String[]{sortField}).ascending() : Sort.by(new String[]{sortField}).descending();
    PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, sort);
    return this.motorPolicyRepository.findAll((Pageable) pageRequest);
  }

  public List<MotorPolicy> AllPolicies() {
    return this.motorPolicyRepository.findAll();
  }

  public Page<MotorPolicy> AllPoliciesPaginated(PageableObj pageableObj) {
    PolicyFilterRequest policyFilterRequest = new PolicyFilterRequest();
    policyFilterRequest.setKeywords(pageableObj.getGlobalFilter());
    Pageable pageable = PageRequest.of(pageableObj.getPage(), pageableObj.getSize());

    Specification<MotorPolicy> specification = new PolicyFilterSpecification().getFilteredPolicies(policyFilterRequest);
    return this.motorPolicyRepository.findAll(specification, pageable );
  }

  private void sendDeleteRequest(String documentName) throws Exception {
    log.info(" are we even deleting anything ,  doc name is ", documentName);
    Optional<File> fileuploaded = this.fileDao.findByName(documentName);
    FileDeleteRequest request = FileDeleteRequest.createFileDeleteRequest(((File) fileuploaded.get()).getName(), ((File) fileuploaded.get()).getDirectory());
    this.fileDeleteService.deleteFile(request);
  }

  @Override
  public Page<MotorPolicyRenewal> renewalsByPolicyIdPaginated(Pageable pageable, Long id) {
    Optional<MotorPolicy> policyOptional = motorPolicyRepository.findById(id);

    if (policyOptional.isPresent()) {
      MotorPolicy policy = policyOptional.get();

      Specification<MotorPolicyRenewal> specification = (root, query, criteriaBuilder) -> {
        return criteriaBuilder.equal(root.get("policy").get("id"), policy.getId());
      };
      // Get the list of renewals for the policy
      return this.motorPolicyRenewalRepository.findAll(specification, pageable);

    } else {
      // Handle the case where the policy is not found
      return Page.empty();
    }

  }
  @Override
  public Page<MotorPolicy> renewalListPaginated(Pageable pageable, RenewalPeriods period) {
    RenewalsToDates renewals =  Utils.renewalsToDates();
    if (period.equals(RenewalPeriods.EXPIRED) ){
      return this.motorPolicyRepository.findAllByPeriodToLessThan(renewals.getToday(), pageable);
    }
    if (period.equals(RenewalPeriods.DUE)) {
      return this.motorPolicyRepository.findByPeriodTo(renewals.getToday(), pageable);
    }
    if (period.equals(RenewalPeriods.DUEIN7DAYS)) {
      return this.motorPolicyRepository.findAllByPeriodToBetween(renewals.getToday(), renewals.getSevenDays(), pageable);
    }
    if (period.equals(RenewalPeriods.DUEIN30DAYS)) {
      return this.motorPolicyRepository.findAllByPeriodToBetween(renewals.getSevenDays(), renewals.getThirtyDaysFromToday(), pageable);
    }
    if (period.equals(RenewalPeriods.DUEIN60DAYS)) {
      return this.motorPolicyRepository.findAllByPeriodToBetween(renewals.getThirtyDaysFromToday(), renewals.getSixtyDaysFromToday(), pageable);
    }
    if (period.equals(RenewalPeriods.DUEIN90DAYS)) {
      return this.motorPolicyRepository.findAllByPeriodToBetween(renewals.getSixtyDaysFromToday(), renewals.getNinetyDaysFromToday(), pageable);
    }
    if (period.equals(RenewalPeriods.DUEIN90PLUSDAYS)) {
      return this.motorPolicyRepository.findAllByPeriodToGreaterThan(renewals.getNinetyDaysFromToday(), pageable);
    }
    return null;

  }

  @Override
  public ResponseEntity<?> renewPolicy(Long id, MotorPolicyRequest policy) {
//    add current policy to history
      this.policyActionBeforeRenewal(id);

//        create new policy with new details

    this.policyActionAfterRenewal(id , policy);

    return ResponseEntity.ok().body(new ApiResponse(true, "Policy renewed successfully"));

  }

  @Override
  public void sendRenewalEmail(PoliciesEnum policyName) {

    log.info("_________start send for {}__________________" , policyName);
//    get all policies due in less than 30 TO 60 days
    List<MotorPolicy> policies = motorPolicyRepository.findAllByPeriodToBetween(Utils.renewalsToDates().getThirtyDaysFromToday(), Utils.renewalsToDates().getSixtyDaysFromToday());
    List<ClientRenewalNotification> clientRenewalNotifications = new ArrayList<>();
    Date today = new Date();
    policies.forEach(policy -> {

              RenewalReminderBodyDTO renewalReminderBodyDTO = new RenewalReminderBodyDTO();
              renewalReminderBodyDTO.setPolicyNo(policy.getPolicyNo());
              renewalReminderBodyDTO.setPolicyName(policyName);
              renewalReminderBodyDTO.setInsured(policy.getInsured().getName());
              renewalReminderBodyDTO.setEmail(policy.getInsured().getEmail());

        if(policy.getPeriodTo().before(today)){
          log.info("expired policy ignoring >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
        else{

          Calendar cal = Calendar.getInstance();
          cal.setTime(policy.getPeriodTo());
          int daysToRenewal = (int) ChronoUnit.DAYS.between(LocalDate.now(), policy.getPeriodTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());


          Optional<ClientRenewalNotification> notification = clientRenewalNotificationRepository.findOneByPolicyNameAndPolicyId(policyName , policy.getId());
          if(notification.isPresent()){
            Date lastNotificationDate = notification.get().getLastNotificationDate();
            int daysSinceNotification = (int) ChronoUnit.DAYS.between(LocalDate.now(), lastNotificationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            if(daysSinceNotification < 0 && daysSinceNotification <= -60){

              if(!Utils.checkEmailAddress(policy.getInsured().getEmail())){
                log.info("xxxxxxxxxxxxxxxxxxxxx   invalid email address {} for client {} policy no {}" , policy.getInsured().getEmail() , policy.getInsured().getName() , policy.getPolicyNo());
              }
              else{

                if(this.emailService.sendRenewalReminder(renewalReminderBodyDTO , daysToRenewal , policyName)){

                  log.info(">>>>>{}>>>>>>>>>>  email send policy insured {} , valid email {} and email {}" ,policyName, policy.getInsured().getName() , Utils.checkEmailAddress(policy.getInsured().getEmail()) , policy.getInsured().getEmail());
                  notification.get().setLastNotificationDate(new Date());
                  clientRenewalNotificationRepository.save(notification.get());
                }
              }



            }else{
              log.info("!!!!!!!!!!!{}!!!!!!!!!!!!!not sending because not 60 days yet", policyName);

            }

          }else{
            if(!Utils.checkEmailAddress(policy.getInsured().getEmail())){
              log.info("xxxxxxxxx{}xxxxxxxxxxxx   invalid email address {} for client {} policy no {}" ,policyName, policy.getInsured().getEmail() , policy.getInsured().getName() , policy.getPolicyNo());
            }
            else {
              log.info(">>>>>>>{}>>>>>>>> in else   email send policy insured {} , valid email {} and email {}", policyName ,policy.getInsured().getName(), Utils.checkEmailAddress(policy.getInsured().getEmail()), policy.getInsured().getEmail());


//              ObjectMapper objectMapper = new ObjectMapper();
//              try {
//                String json = objectMapper.writeValueAsString(renewalReminderBodyDTO);
//                jmsTemplate.convertAndSend("reminderQueue", json);
//              } catch (JsonProcessingException e) {
//                // Handle JSON processing exception
//                e.printStackTrace();
//              }



              if(this.emailService.sendRenewalReminder(renewalReminderBodyDTO , daysToRenewal , policyName)){
              ClientRenewalNotification clientRenewalNotification = new ClientRenewalNotification();
              clientRenewalNotification.setPolicyName(policyName);
              clientRenewalNotification.setPolicyId(policy.getId());
              clientRenewalNotification.setLastNotificationDate(new Date());
              clientRenewalNotifications.add(clientRenewalNotification);

            }

            }

          }
        }
    }

    );

    if(!clientRenewalNotifications.isEmpty()){
      clientRenewalNotificationRepository.saveAll(clientRenewalNotifications);
    }
  }


  @Override
  @Transactional
  public ResponseEntity<?> createPolicyV2(MotorPolicyRequest policyRequest, MultipartFile[] files, MultipartFile policyExcel) throws Exception {

MotorPolicy policy = new MotorPolicy();
    BeanUtils.copyProperties(policyRequest, policy ,CustomBeanUtils.getNullPropertyNames(policyRequest));

    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>policy data {} " , policy);

    List<MotorPolicyVehicle> motorPolicyVehicles = new ArrayList<>();


    
    List<MotorPolicyDocument> motorPolicyDocuments = new ArrayList<>();
//    check files is empty
    log.info("files {} ", files.length);
    if (files.length > 0) {
      for (MultipartFile file : files) {
        FileUploadRequest fileUploadRequest = FileUploadRequest.createFileUploadRequest(file, "allassetrisk2/parent");
        File savedFile = this.fileUploadService.uploadFile(fileUploadRequest);
        String filename = FileDto.createFileDto(savedFile).getFileName();
        MotorPolicyDocument motorPolicyDocument = new MotorPolicyDocument();
        motorPolicyDocument.setFileName(filename);
        motorPolicyDocument.setOriginalName(file.getOriginalFilename());
        motorPolicyDocument.setMotorPolicyEnumDocumentType(MotorPolicyEnumDocumentType.PARENT);
        motorPolicyDocument.setPolicy(policy);
        motorPolicyDocuments.add(motorPolicyDocument);
      }
    }
    policy.setMotorPolicyDocuments(motorPolicyDocuments);
    policy.setMotorVehicles(motorPolicyVehicles);
    policy.setCreatedAt(new Date());
    policy.setCreatedBy("admin");
//    Double premium = this.policyService.calculatePremium(policy.getPeriodFrom(), policy.getPeriodTo(), policy.getSumInsured(), policy.getRate());
//    policy.setPremium(premium);
//    Double stampDuty = this.policyService.calculateStampDuty(premium);
//    policy.setStampDuty(stampDuty);
//    policy.setGovernmentLevy(this.policyService.calculateGvtLevy(premium, stampDuty));


    MotorPolicy saved = motorPolicyRepository.save(policy);
    if (saved != null) {

      return ResponseEntity.ok().body(new ApiResponse(true, "Policy Created"));
    }
    return ResponseEntity.badRequest().body(new ApiResponse(false, "Policy Not Created"));
  }



  @Override
  public ResponseEntity<?> updatePolicyV2(MotorPolicyRequest policyRequest, MultipartFile policyExcel) {

    
    Optional<MotorPolicy> existingPolicy = this.motorPolicyRepository.findById(policyRequest.getId());

    if (!existingPolicy.isPresent()){
      return ResponseEntity.notFound().build();
    }

    List<MotorPolicyVehicle> existingVehicles = existingPolicy.get().getMotorVehicles();
    BeanUtils.copyProperties(policyRequest, existingPolicy.get(), CustomBeanUtils.getNullPropertyNames(policyRequest));

    motorPolicyVehicleRepository.deleteAllInBatch(existingVehicles);
    this.motorPolicyRepository.save(existingPolicy.get());
    return ResponseEntity.ok().body(new ApiResponse(true, "Policy Updated"));
  }

  @Override
  public ResponseEntity<?> addDocumentsV2(Long policyId, List<FileUploadDetails> documents) {
    Optional<MotorPolicy> existingPolicy = this.motorPolicyRepository.findById(policyId);
    if (!existingPolicy.isPresent()){
      return ResponseEntity.notFound().build();
    }
    List<MotorPolicyDocument> motorPolicyDocuments = new ArrayList<>();
    documents.forEach(fileUploadDetails -> {
      MotorPolicyDocument motorPolicyDocument = new MotorPolicyDocument();
      motorPolicyDocument.setFileName(fileUploadDetails.getFileName());
      motorPolicyDocument.setOriginalName(fileUploadDetails.getOriginalName());
      motorPolicyDocument.setMotorPolicyEnumDocumentType(MotorPolicyEnumDocumentType.PARENT);
      motorPolicyDocument.setPolicy(existingPolicy.get());
      motorPolicyDocuments.add(motorPolicyDocument);
    });

    existingPolicy.get().getMotorPolicyDocuments().addAll(motorPolicyDocuments);
    this.motorPolicyRepository.save(existingPolicy.get());

    return ResponseEntity.ok().body(new ApiResponse(true, "Documents Added"));

  }


  void policyActionBeforeRenewal(Long id){
    Optional<MotorPolicy> existingPolicy = this.motorPolicyRepository.findById(id);

    MotorPolicyRenewal renewal = new MotorPolicyRenewal();
    renewal.setPolicy(existingPolicy.get());
    renewal.setPolicyStatus(existingPolicy.get().getPolicyStatus());
    renewal.setInsured(existingPolicy.get().getInsured());
    renewal.setCurrency(existingPolicy.get().getCurrency());
    renewal.setFleetIndividual(existingPolicy.get().getFleetIndividual());
    renewal.setPeriodFrom(existingPolicy.get().getPeriodFrom());
    renewal.setPeriodTo(existingPolicy.get().getPeriodTo());
//    copy renewal existingPolicy.get().getMotorPolicyDocuments() to renewal
    BeanUtils.copyProperties(existingPolicy.get().getMotorPolicyDocuments(), renewal.getMotorPolicyDocuments());
//    BeanUtils.copyProperties(existingPolicy.get().getMotorVehicles(), renewal.getMotorVehicles());

    renewal.setPolicyNo(existingPolicy.get().getPolicyNo());
    renewal.setTmpVehicles(existingPolicy.get().getTmpVehicles());
    renewal.setCoverType(existingPolicy.get().getCoverType());
    renewal.setSumInsured(existingPolicy.get().getSumInsured());
    renewal.setPremium(existingPolicy.get().getPremium());
    renewal.setRate(existingPolicy.get().getRate());
    renewal.setStampDuty(existingPolicy.get().getStampDuty());
    renewal.setGovernmentLevy(existingPolicy.get().getGovernmentLevy());
    motorPolicyRenewalRepository.save(renewal);
  }
  String policyActionAfterRenewal(Long id, MotorPolicyRequest policy){
    
    Optional<MotorPolicy> existingPolicy = this.motorPolicyRepository.findById(id);

    List<MotorPolicyVehicle> existingVehicles = existingPolicy.get().getMotorVehicles();
    BeanUtils.copyProperties(policy, existingPolicy.get(), CustomBeanUtils.getNullPropertyNames(policy));
    motorPolicyVehicleRepository.deleteAllInBatch(existingVehicles);

    this.motorPolicyRepository.save(existingPolicy.get());
    return null;

  }

}

