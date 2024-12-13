package com.insurance.system.shared.filestorage.context.store;

public class FileNotFoundException extends RuntimeException {
  public FileNotFoundException(String message) {
    super(message);
  }
}
