package com.insurance.system.shared.filestorage.context.store;

import org.springframework.core.io.Resource;
import com.insurance.system.shared.filestorage.context.File;

public class DownloadResource {
  private Resource resource;
  
  private File file;
  
  public void setResource(Resource resource) {
    this.resource = resource;
  }
  
  public void setFile(File file) {
    this.file = file;
  }
  

  
  public String toString() {
    return "DownloadResource(resource=" + getResource() + ", file=" + getFile() + ")";
  }

//  TODO chnaged
//
//  public static DownloadResourceBuilder builder() {
//    return new DownloadResourceBuilder();
//  }
  
  public DownloadResource() {}
  
  public DownloadResource(Resource resource, File file) {
    this.resource = resource;
    this.file = file;
  }
  
  public Resource getResource() {
    return this.resource;
  }
  
  public File getFile() {
    return this.file;
  }
}
