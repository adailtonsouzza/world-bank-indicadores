package br.com.worldbank.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    private Indicador indicator;
    private Pais country;
    private String countryiso3code;
    private String date;
    private Double value;
    private String unit;
    private String obs_status;
    private Integer decimal;
}
