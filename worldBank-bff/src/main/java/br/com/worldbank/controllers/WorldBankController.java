package br.com.worldbank.controllers;

import br.com.worldbank.domain.ResponseData;
import br.com.worldbank.services.WorldBankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
@Tag(name = "API do Banco Mundial", description = "API para acessar dados do Banco Mundial")
@RequiredArgsConstructor
public class WorldBankController {


    private final WorldBankService worldBankService;

    @GetMapping("/indicador")
    @Operation(summary = "Obter Indicador de Pobreza", description = "Retorna dados do indicador de pobreza para um determinado código de país")
    public ResponseEntity<List<ResponseData>> getPovertyIndicator(@RequestParam @NotBlank String codigoPais) {
        return ResponseEntity.ok(worldBankService.getIndicadorProbreza(codigoPais));
    }
}
