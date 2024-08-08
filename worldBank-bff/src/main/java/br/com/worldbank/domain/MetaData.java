package br.com.worldbank.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetaData {

    private Integer page;
    private Integer pages;
    private Integer per_page;
    private Integer total;
    private String sourceid;
    private String lastupdated;
}
