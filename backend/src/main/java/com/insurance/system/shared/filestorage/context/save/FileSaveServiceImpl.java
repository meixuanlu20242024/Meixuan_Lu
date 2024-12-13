package com.insurance.system.shared.filestorage.context.save;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.insurance.system.shared.filestorage.context.File;
import com.insurance.system.shared.filestorage.context.FileDao;

@Service
public class FileSaveServiceImpl implements FileSaveService {
  private static final Logger LOGGER = LoggerFactory.getLogger(FileSaveServiceImpl.class);
  
  private final FileDao fileDao;
  
  public FileSaveServiceImpl(FileDao fileDao) {
    this.fileDao = fileDao;
  }
  
  public File saveFile(File file) {
    return saveFileInTheDatabse(file);
  }
  
  private File saveFileInTheDatabse(File file) {
    try {
      LOGGER.info("Saving file: {}", file);
      return (File)this.fileDao.save(file);
    } catch (DataAccessException e) {
      LOGGER.error("Failed to save file: {}", file);
      throw new IllegalStateException(e.getMessage());
    } 
  }
}
