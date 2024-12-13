package com.insurance.system.shared.filestorage.context.upload;

import com.insurance.system.shared.filestorage.context.File;

public interface FileUploadService {
  File uploadFile(FileUploadRequest paramFileUploadRequest) throws Exception;
  
  File upload(FileUploadContext paramFileUploadContext) throws Exception;
}
