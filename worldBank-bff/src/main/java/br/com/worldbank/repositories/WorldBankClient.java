package br.com.worldbank.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "worldBankClient", url = "http://api.worldbank.org/v2")
public interface WorldBankClient {

    @GetMapping(value = "/country/{countryCode}/indicator/SI.POV.DDAY?format=json",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Object> getIndicadorProbreza(@PathVariable("countryCode") String paisCode);
}
