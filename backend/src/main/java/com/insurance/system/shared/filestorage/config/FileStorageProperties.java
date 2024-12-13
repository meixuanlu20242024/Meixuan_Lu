package com.insurance.system.shared.filestorage.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileStorageProperties {
  @Value("${file.uploadDir}")
  private String uploadRoot = "";
  
  @Value("${file.fileTypes}")
  private String fileTypes;
  
  public String getUploadRoot() {
    return this.uploadRoot;
  }
  
  public List<String> getFileTypes() {
    List<String> temp = Arrays.asList(this.fileTypes.split(","));
    ArrayList<String> types = new ArrayList<>(temp);
    types.addAll((Collection<? extends String>)temp.stream().map(String::toUpperCase).collect(Collectors.toList()));
    return types;
  }
}
