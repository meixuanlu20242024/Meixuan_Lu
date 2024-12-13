package com.insurance.system.shared.filestorage.context.download;

import com.insurance.system.shared.filestorage.context.store.DownloadResource;

@FunctionalInterface
public interface FileDownloadService {
  DownloadResource downloadFile(String paramString);
}
