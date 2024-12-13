package com.insurance.system.shared.domain.payload;

import lombok.Data;

@Data
public class PageableObj {

    private int page  ;
    private int size;
    private int sortOrder;
    private Filters filters;
    private String globalFilter;


    @Data
    public static class Filters {
        // You can add fields for the Filters object if needed
    }
}
