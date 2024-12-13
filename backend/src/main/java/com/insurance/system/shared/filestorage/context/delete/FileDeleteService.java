package com.insurance.system.shared.filestorage.context.delete;

public interface FileDeleteService {
  void deleteFile(FileDeleteRequest paramFileDeleteRequest) throws Exception;
  
  void createDeleteRequest(String paramString) throws Exception;
}
