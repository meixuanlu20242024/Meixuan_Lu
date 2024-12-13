package com.insurance.system.shared.filestorage.context.store;

import java.io.IOException;

import com.insurance.system.shared.filestorage.context.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
  String storeFile(MultipartFile paramMultipartFile, String paramString) throws IOException;
  
  DownloadResource downloadFile(File paramFile);

}
