package br.com.worldbank.controllers;

import br.com.worldbank.domain.ResponseData;
import br.com.worldbank.services.WorldBankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorldBankControllerTest {


    @Mock
    private WorldBankService worldBankService;

    @InjectMocks
    private WorldBankController worldBankController;

    @BeforeEach
    void setUp() {
    }


    @Test
    void getPovertyIndicatorReturnsValidData() {
        String paisCode = "BRA";
        List<ResponseData> mockResponse = Collections.singletonList(new ResponseData());
        when(worldBankService.getIndicadorProbreza(paisCode)).thenReturn(mockResponse);

        ResponseEntity<List<ResponseData>> response = worldBankController.getPovertyIndicator(paisCode);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(worldBankService, times(1)).getIndicadorProbreza(paisCode);
    }

    @Test
    void getPovertyIndicatorReturnsEmptyListWhenNoData() {
        String paisCode = "BRA";
        when(worldBankService.getIndicadorProbreza(paisCode)).thenReturn(Collections.emptyList());

        ResponseEntity<List<ResponseData>> response = worldBankController.getPovertyIndicator(paisCode);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(worldBankService, times(1)).getIndicadorProbreza(paisCode);
    }

    @Test
    void getPovertyIndicatorHandlesNullResponse() {
        String paisCode = "BRA";
        when(worldBankService.getIndicadorProbreza(paisCode)).thenReturn(null);

        ResponseEntity<List<ResponseData>> response = worldBankController.getPovertyIndicator(paisCode);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(worldBankService, times(1)).getIndicadorProbreza(paisCode);
    }

    @Test
    void getPovertyIndicatorThrowsException() {
        String paisCode = "BRA";
        when(worldBankService.getIndicadorProbreza(paisCode)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> worldBankController.getPovertyIndicator(paisCode));

    }
}