package com.projek.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    private Long id;
    private String nomor_tlp;
    private Integer status;
    private String path;
    private String description;
    private String updated_at;
    private String updated_by;
}
