package com.insurance.system.shared.filestorage.api;


import com.insurance.system.shared.domain.payload.FileUploadDTO;
import com.insurance.system.shared.domain.payload.FileUploadDetails;
import com.insurance.system.shared.filestorage.context.File;
import com.insurance.system.shared.filestorage.context.FileDto;
import com.insurance.system.shared.filestorage.context.ValidateFileResponse;
import com.insurance.system.shared.filestorage.context.delete.FileDeleteRequest;
import com.insurance.system.shared.filestorage.context.delete.FileDeleteService;
import com.insurance.system.shared.filestorage.context.download.FileDownloadService;
import com.insurance.system.shared.filestorage.context.store.DownloadResource;
import com.insurance.system.shared.filestorage.context.upload.FileUploadRequest;
import com.insurance.system.shared.filestorage.context.upload.FileUploadService;
import com.insurance.system.shared.filestorage.context.verify.FileVerificationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class FileRestControllerv2 {
  private static final Logger LOGGER = LoggerFactory.getLogger(FileRestControllerv2.class);

  private final FileUploadService fileUploadService;

  private final FileDeleteService fileDeleteService;

  private final FileDownloadService fileDownloadService;

  private final FileVerificationService fileVerificationService;

  @Autowired
  public FileRestControllerv2(FileUploadService fileUploadService, FileDeleteService fileDeleteService, FileDownloadService fileDownloadService, FileVerificationService fileVerificationService) {
    this.fileUploadService = fileUploadService;
    this.fileDeleteService = fileDeleteService;
    this.fileDownloadService = fileDownloadService;
    this.fileVerificationService = fileVerificationService;
  }

  @PostMapping({"/v2/files"})
  public FileDto uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(value = "directory", required = false) String directory) throws Exception {
    LOGGER.info("Uploading file: {} ", file);
    FileUploadRequest fileUploadRequest = FileUploadRequest.createFileUploadRequest(file, directory);
    File savedFile = this.fileUploadService.uploadFile(fileUploadRequest);
    return FileDto.createFileDto(savedFile);
  }
  @PostMapping({"/v2/files/upload"})
  public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files , @RequestParam(value = "directory", required = false) String directory) throws Exception {

log.info("Uploading file: {} ", files.length);

    FileUploadDTO fileUploadDetail = new FileUploadDTO();
    List<FileUploadDetails> fileUploadDetails = new ArrayList<>();
    for (MultipartFile file: files) {

      FileUploadRequest fileUploadRequest = FileUploadRequest.createFileUploadRequest(file, directory);
      File savedFile = this.fileUploadService.uploadFile(fileUploadRequest);
      String  filename = FileDto.createFileDto(savedFile).getFileName();

      FileUploadDetails fd = new FileUploadDetails();
      fd.setFileName(filename);
      fd.setDirectory(directory);
      fd.setOriginalName(file.getOriginalFilename());
      fileUploadDetails.add(fd);


    }
     fileUploadDetail.setDetails(fileUploadDetails);
    return ResponseEntity.ok().body(fileUploadDetail);

  }

//  @PostMapping(value = {"/v2/files"}, consumes = {"multipart/form-data"})
//  public File upload(FileUploadContext fileUploadContext, HttpServletRequest httpServletRequest) throws Exception {
//    fileUploadContext.setServletRequest((ServletRequest)httpServletRequest);
//    return this.fileUploadService.upload(fileUploadContext);
//  }
//
  @DeleteMapping({"/v1/files"})
  public void deleteFile(@RequestParam("fileName") String fileName, @RequestParam(value = "directory", required = false) String directory) throws Exception {
    LOGGER.info("Deleting file request: {} ", fileName);
    FileDeleteRequest fileDeleteRequest = FileDeleteRequest.createFileDeleteRequest(fileName, directory);
    this.fileDeleteService.deleteFile(fileDeleteRequest);
  }

  @GetMapping({"/v2/files/{fileName}"})
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
    String downloadFileName;
    DownloadResource downloadResource = this.fileDownloadService.downloadFile(fileName);
    String contentType = Objects.nonNull(getContentType(request, downloadResource.getResource())) ? getContentType(request, downloadResource.getResource()) : "application/octet-stream";
    if (Objects.nonNull(downloadResource.getFile().getOriginalFileName())) {
      String originalFileName = downloadResource.getFile().getOriginalFileName();
      String type = downloadResource.getFile().getContentType();
      if (originalFileName.endsWith(type)) {
        downloadFileName = originalFileName;
      } else {
        downloadFileName = originalFileName + "." + type;
      }
    } else {
      downloadFileName = fileName;
    }
    return ((ResponseEntity.BodyBuilder)ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(contentType))

      .header("Content-Disposition", new String[] { "attachment; filename=\"" + downloadFileName + "\"" })).body(downloadResource.getResource());
  }

  @GetMapping({"/v1/files/verify"})
  public ValidateFileResponse validateFile(@RequestParam("fileName") String fileName) {
    return this.fileVerificationService.verify(fileName);
  }

  private String getContentType(HttpServletRequest httpServletRequest, Resource resource) {
    String contentType = "application/octet-stream";
    try {
      contentType = httpServletRequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      LOGGER.info("Could not determine file type.");
    }
    return contentType;
  }
}
