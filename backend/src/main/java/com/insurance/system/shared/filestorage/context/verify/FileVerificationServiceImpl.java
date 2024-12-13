package com.insurance.system.shared.filestorage.context.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.insurance.system.shared.filestorage.config.FileStorageProperties;
import com.insurance.system.shared.filestorage.context.File;
import com.insurance.system.shared.filestorage.context.FileDao;
import com.insurance.system.shared.filestorage.context.ValidateFileResponse;

@Service
public class FileVerificationServiceImpl implements FileVerificationService {
  private static final Logger log = LoggerFactory.getLogger(FileVerificationServiceImpl.class);
  
  private final FileDao fileDao;
  
  private final FileStorageProperties fileStorageProperties;
  
  @Value("${file.fileTypes}")
  private String fileTypes;
  
  public FileVerificationServiceImpl(FileDao fileDao, FileStorageProperties fileStorageProperties) {
    this.fileDao = fileDao;
    this.fileStorageProperties = fileStorageProperties;
  }
  
  public ValidateFileResponse verify(String fileName) {
    Optional<File> file = this.fileDao.findByName(fileName);
    ValidateFileResponse validateFileResponse = new ValidateFileResponse();
    validateFileResponse.setValid(Boolean.valueOf(file.isPresent()));
    return validateFileResponse;
  }
  
  public List<String> validateFileForUpload(MultipartFile file, String fileref) {
    List<String> errors = new ArrayList<>();
    if (file.isEmpty())
      errors.add(fileref + " field cannot be empty"); 
    String[] stringArray = this.fileTypes.split(",");
    List<String> contentTypes = Arrays.asList(stringArray);
    String contentType = file.getOriginalFilename();
    String extention = contentType.substring(contentType.lastIndexOf(".") + 1);
    if (!contentTypes.contains(extention) && !extention.equals(""))
      errors.add(fileref + " file is of extentions '" + extention + "' which is not allowed !"); 
    return errors;
  }
}
