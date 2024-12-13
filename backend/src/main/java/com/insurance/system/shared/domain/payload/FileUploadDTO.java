package com.insurance.system.shared.domain.payload;

import lombok.Data;

import java.util.List;


@Data
public class    FileUploadDTO {
    private List<FileUploadDetails> details ;

}