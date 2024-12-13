package com.insurance.system.shared.filestorage.context.verify;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.insurance.system.shared.filestorage.context.ValidateFileResponse;

public interface FileVerificationService {
  ValidateFileResponse verify(String paramString);
  
  List<String> validateFileForUpload(MultipartFile paramMultipartFile, String paramString);
}
