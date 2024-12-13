package com.insurance.system.shared.filestorage.context.download;

import com.insurance.system.shared.filestorage.context.store.DownloadResource;

public interface FileCloudDownloadService {
  DownloadResource downloadFile(String paramString);
}
