package br.com.worldbank.services;

import br.com.worldbank.domain.ResponseData;
import br.com.worldbank.exceptions.TimeoutException;
import br.com.worldbank.repositories.WorldBankClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class WorldBankServiceTest {
    @Mock
    private WorldBankClient worldBankClient;

    @InjectMocks
    private WorldBankService worldBankService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getIndicadorProbrezaReturnsValidData() throws SocketTimeoutException {
        String paisCode = "BRA";
        List<Object> mockResponse = Collections.singletonList(new ResponseData());
        when(worldBankClient.getIndicadorProbreza(paisCode)).thenReturn(mockResponse);

        List<ResponseData> actualData = worldBankService.getIndicadorProbreza(paisCode);

        assertNotNull(actualData);
        assertEquals(1, actualData.size());
        verify(worldBankClient, times(1)).getIndicadorProbreza(paisCode);
    }

    @Test
    void getIndicadorProbrezaReturnsEmptyListWhenNoData() throws SocketTimeoutException {
        String paisCode = "BRA";
        when(worldBankClient.getIndicadorProbreza(paisCode)).thenReturn(Collections.emptyList());

        List<ResponseData> actualData = worldBankService.getIndicadorProbreza(paisCode);

        assertTrue(actualData.isEmpty());
        verify(worldBankClient, times(1)).getIndicadorProbreza(paisCode);
    }

    @Test
    void getIndicadorProbrezaHandlesNullResponse() throws SocketTimeoutException {
        String paisCode = "BRA";
        when(worldBankClient.getIndicadorProbreza(paisCode)).thenReturn(null);

        List<ResponseData> actualData = worldBankService.getIndicadorProbreza(paisCode);

        assertNull(actualData);
        verify(worldBankClient, times(1)).getIndicadorProbreza(paisCode);
    }

    @Test
    void getIndicadorProbrezaThrowsGenericException() throws Exception {
        String paisCode = "BRA";
        when(worldBankClient.getIndicadorProbreza(paisCode)).thenThrow(new RuntimeException());

        assertThrows(TimeoutException.class, () -> worldBankService.getIndicadorProbreza(paisCode));
        verify(worldBankClient, times(1)).getIndicadorProbreza(paisCode);
    }
}