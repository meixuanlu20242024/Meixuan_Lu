package com.insurance.system.shared.filestorage.context.save;

import com.insurance.system.shared.filestorage.context.File;

@FunctionalInterface
public interface FileSaveService {
  File saveFile(File paramFile);
}
