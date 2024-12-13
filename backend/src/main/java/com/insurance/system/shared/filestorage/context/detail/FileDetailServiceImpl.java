package com.insurance.system.shared.filestorage.context.detail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.insurance.system.shared.filestorage.context.File;
import com.insurance.system.shared.filestorage.context.FileDao;

@Service
public class FileDetailServiceImpl implements FileDetailService {
  private static final Logger log = LoggerFactory.getLogger(FileDetailServiceImpl.class);
  
  private final FileDao fileDao;
  
  public FileDetailServiceImpl(FileDao fileDao) {
    this.fileDao = fileDao;
  }
  
  public File getFile(String fileName) {
    try {
      log.info("Fetching file details from database with fileName: {}", fileName);
      return (File)this.fileDao.findByName(fileName)
        .orElseThrow(() -> new Exception("File metadata record was not found"));
    } catch (Exception e) {
      log.error("Failed to fetch file details from database with fileName: {} because of: {}", fileName, e.getMessage());
      throw new IllegalStateException("The system failed to fetch file details from the database, kindly contact your System's Administrator");
    } 
  }
}
