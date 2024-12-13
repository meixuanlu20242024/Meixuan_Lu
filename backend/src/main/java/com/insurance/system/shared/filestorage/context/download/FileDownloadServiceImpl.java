package com.insurance.system.shared.filestorage.context.download;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.insurance.system.shared.filestorage.context.File;
import com.insurance.system.shared.filestorage.context.detail.FileDetailService;
import com.insurance.system.shared.filestorage.context.store.DownloadResource;
import com.insurance.system.shared.filestorage.context.store.FileStorageService;

@Service
public class FileDownloadServiceImpl implements FileDownloadService {
  private static final Logger log = LoggerFactory.getLogger(FileDownloadServiceImpl.class);
  
  private final FileStorageService fileStorageService;
  
  private final FileDetailService fileDetailService;
  
  public FileDownloadServiceImpl(FileStorageService fileStorageService, FileDetailService fileDetailService) {
    this.fileStorageService = fileStorageService;
    this.fileDetailService = fileDetailService;
  }
  
  public DownloadResource downloadFile(String fileName) {

    log.info("Downloading file with fileName: {}", fileName);
    File file = this.fileDetailService.getFile(Objects.<String>requireNonNull(fileName, "File name should be provided."));
    log.info("if here we are goog ___________________________");
    return this.fileStorageService.downloadFile(file);
  }
}
