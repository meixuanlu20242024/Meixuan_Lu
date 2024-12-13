package com.insurance.system.shared.domain.payload;

import lombok.Data;



@Data
public  class FileUploadDetails {


    private String fileName;
    private String originalName;
    private String directory;

}