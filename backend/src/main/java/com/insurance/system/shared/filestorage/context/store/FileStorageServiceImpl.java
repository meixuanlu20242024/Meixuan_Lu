package com.insurance.system.shared.filestorage.context.store;



import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Objects;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.insurance.system.shared.filestorage.config.FileStorageProperties;

@Service

@Slf4j
public class FileStorageServiceImpl implements FileStorageService {
  private final FileStorageProperties fileStorageProperties;
  


  
  @Value("${do.space.baseFolder}")
  private String baseFolder;
  
  String FOLDER = "files/";
  
  public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
    this.fileStorageProperties = fileStorageProperties;
  }
  
  public String storeFile(MultipartFile file, String directory) throws IOException {
    Objects.requireNonNull(file);
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    checkIfFileNameContainsInvalidCharacters(fileName);
    fileName = generateUniqueFileName(fileName);
    saveFileToLocal(file, this.baseFolder + "/" + directory ,  fileName);
    return fileName;
  }


//  todo changed download resouce
//  public DownloadResource downloadFile(File file) {
//    S3Object fileObject = this.s3Client.getObject(new GetObjectRequest(this.doSpaceBucket, this.baseFolder + "/" + file.getDirectory() + "/" + file.getName()));
//    S3ObjectInputStream inputStream = fileObject.getObjectContent();
//    InputStreamResource inputStreamResource = new InputStreamResource((InputStream)inputStream);
//
//    if(!inputStreamResource.exists()) {
//      throw new FileNotFoundException("File not found " + file.getName());
//    } else {
//      return new DownloadResource(inputStreamResource, file);
//    }
//
//  }

  public DownloadResource downloadFile(com.insurance.system.shared.filestorage.context.File file) {
    // Define the default public directory path (replace with your actual path)
    String fullpath = this.baseFolder + "//" + file.getDirectory() + "//"+ file.getName();

    InputStream inputStream = null;
    try {
      inputStream = Files.newInputStream(Paths.get(fullpath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

    return new DownloadResource(inputStreamResource, file);
  }




  
  private void persistFile(MultipartFile file, Path path) {
    try {
      Files.copy(file.getInputStream(), path, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
    } catch (IOException ex) {
      throw new IllegalStateException("Could not store file " + file.getName() + ". Please try again!");
    } 
  }
  
  private String generateUniqueFileName(String fileName) {
    String fileExtension = "";
    try {
      fileExtension = fileName.substring(fileName.lastIndexOf("."));
    } catch (Exception exception) {}
    return UUID.randomUUID().toString() + fileExtension;
  }
  
  private void checkIfFileNameContainsInvalidCharacters(String fileName) {
    if (fileName.contains(".."))
      throw new IllegalStateException("Filename contains invalid path sequence " + fileName); 
  }
  
  private Path getFileStorageLocation(String directory) {
    if (Objects.nonNull(directory))
      return Paths.get(this.fileStorageProperties.getUploadRoot() + directory, new String[0])
        .toAbsolutePath().normalize(); 
    return Paths.get(this.fileStorageProperties.getUploadRoot(), new String[0])
      .toAbsolutePath().normalize();
  }
  
  private void createDirectoriesIfThereDoNotExist(Path fileStorageLocation) {
    try {
      Files.createDirectories(fileStorageLocation, (FileAttribute<?>[])new FileAttribute[0]);
    } catch (Exception ex) {
      throw new IllegalStateException("Could not create the directory where the uploaded files will be stored." + ex);
    } 
  }
  


  private void saveFileToLocal(MultipartFile multipartFile, String fullPath, String fileName) throws IOException {
    // Create the directory if it doesn't exist
    java.io.File dir = new java.io.File(fullPath);

    log.info("Creating directory: " + fullPath);
    if (!dir.exists()) {
      dir.mkdirs(); // Creates all necessary parent directories
    }




    try (FileOutputStream fos = new FileOutputStream(fullPath+"/"+fileName)) {
      log.info("Writing file: " + fullPath+"/"+fileName);
      fos.write(multipartFile.getBytes());  // Write file content
    }
  }
}
