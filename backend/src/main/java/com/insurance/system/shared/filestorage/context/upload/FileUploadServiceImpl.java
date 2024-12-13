package com.insurance.system.shared.filestorage.context.upload;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.insurance.system.shared.filestorage.config.FileStorageProperties;
import com.insurance.system.shared.filestorage.context.File;
import com.insurance.system.shared.filestorage.context.FileDao;
import com.insurance.system.shared.filestorage.context.store.FileStorageService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
  private static final Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);
  
  private final FileDao fileDao;
  
  private final FileStorageService fileStorageService;
  
  private final FileStorageProperties fileStorageProperties;
  
  public FileUploadServiceImpl(FileDao fileDao, FileStorageService fileStorageService, FileStorageProperties fileStorageProperties) {
    this.fileDao = fileDao;
    this.fileStorageService = fileStorageService;
    this.fileStorageProperties = fileStorageProperties;
  }
  
  public File uploadFile(FileUploadRequest fileUploadRequest) throws Exception {
    Objects.requireNonNull(fileUploadRequest, "File upload request cannot be null");
    MultipartFile multipartFile = fileUploadRequest.getFile();
    if (Objects.isNull(multipartFile))
      throw new Exception("File must be provided"); 
    String fileType = validateFileType(fileUploadRequest.getFile());
    String fileName = storeFileOnFileServer(fileUploadRequest.getFile(), fileUploadRequest.getDirectory());
    String originalFileName = fileUploadRequest.getFile().getOriginalFilename();
    File file = File.createFile(fileName, fileUploadRequest.getDirectory(), originalFileName, fileType);
    return (File)this.fileDao.save(file);
  }
  
  private String validateFileType(MultipartFile file) throws Exception {
    if (Objects.isNull(file))
      throw new Exception("File  cannot be null"); 
    String originalFileName = file.getOriginalFilename();
    if (Objects.isNull(originalFileName))
      throw new Exception("Client not supported in uploading files"); 
    String fileName = StringUtils.cleanPath(originalFileName);
    for (String fileNameExtension : this.fileStorageProperties.getFileTypes()) {
      if (fileName.endsWith(fileNameExtension))
        return fileNameExtension; 
    } 
    throw new Exception("File type is not accepted");
  }
  
  private String storeFileOnFileServer(MultipartFile file, String directory) throws Exception {
    if (Objects.isNull(file))
      throw new Exception("File  cannot be null"); 
    return this.fileStorageService.storeFile(file, directory);
  }
  
  public File upload(FileUploadContext fileUploadContext) throws Exception {
    return uploadFile(FileUploadRequest.createFileUploadRequest(fileUploadContext.getFile(), null));
  }
}
