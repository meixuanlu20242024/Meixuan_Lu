package com.insurance.system.shared.filestorage.context.delete;

import com.insurance.system.shared.filestorage.context.File;
import com.insurance.system.shared.filestorage.context.FileDao;
import com.insurance.system.shared.filestorage.context.detail.FileDetailService;
import com.insurance.system.shared.filestorage.context.detail.FileDetailServiceImpl;
import com.insurance.system.shared.filestorage.context.store.FileStorageService;
import lombok.Lombok;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FileDeleteServiceImpl implements FileDeleteService {
  private static final Logger LOGGER = LoggerFactory.getLogger(FileDetailServiceImpl.class);
  
  private final FileDao fileDao;
  
  private final FileStorageService fileStorageService;
  
  private final FileDetailService fileDetailService;
  
  public FileDeleteServiceImpl(FileDetailService fileDetailService, FileDao fileDao, FileStorageService fileStorageService) {
    this.fileDao = fileDao;
    this.fileStorageService = fileStorageService;
    this.fileDetailService = fileDetailService;
  }
  
  public void deleteFile(FileDeleteRequest fileDeleteRequest) throws Exception {
    LOGGER.info("FileDeleteRequest: {}", fileDeleteRequest);
    validateRequest(fileDeleteRequest);
    File file = getFileByFileName(fileDeleteRequest.getFileName());
    deleteFileFromDatabase(file.getName());
    String fileName = file.getName();
    //deleteFileFromFileServer(fileName, fileDeleteRequest.getDirectory());
  }
  
  public void createDeleteRequest(String documentName) throws Exception {
//    commented out to block deletion of files

//    Optional<File> fileuploaded = this.fileDao.findByName(documentName);
//    FileDeleteRequest request = FileDeleteRequest.createFileDeleteRequest(((File)fileuploaded.get()).getName(), ((File)fileuploaded.get()).getDirectory());
//    deleteFile(request);
  }
  
  private void validateRequest(FileDeleteRequest fileDeleteRequest) throws Exception {
    try {
      LOGGER.info("Validating request: {}", fileDeleteRequest);
      Lombok.checkNotNull(fileDeleteRequest, "FileDeleteRequest cannot be null");
    } catch (NullPointerException e) {
      LOGGER.error("Validation error: {}", e.getMessage());
      throw new Exception(e.getMessage());
    } 
  }
  
  private File getFileByFileName(String fileName) throws Exception {
    try {
      LOGGER.info("Retrieving file details with fileName: {}", fileName);
      return this.fileDetailService.getFile(fileName);
    } catch (NullPointerException|IllegalArgumentException e) {
      LOGGER.error("Failed to retrieve file details because of", e.getMessage());
      throw new Exception(e.getMessage());
    } 
  }
  
  private void deleteFileFromDatabase(String fileName) {
    try {
      LOGGER.info("Deleting file with name {} from database.", fileName);
      this.fileDao.deleteAllByName(fileName);
    } catch (DataAccessException e) {
      LOGGER.error("Failed to delete file details from the database", e.getMessage());
      throw new IllegalStateException("Failed to delete file record from the database");
    } 
  }
  

}
