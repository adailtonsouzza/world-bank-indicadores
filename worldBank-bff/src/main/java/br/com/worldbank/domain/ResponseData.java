package br.com.worldbank.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseData {

    private MetaData metadata;
    private List<Data> data;
}
