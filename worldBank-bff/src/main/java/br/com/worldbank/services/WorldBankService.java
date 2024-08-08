package br.com.worldbank.services;

import br.com.worldbank.domain.ResponseData;
import br.com.worldbank.exceptions.TimeoutException;
import br.com.worldbank.repositories.WorldBankClient;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorldBankService {


    private final WorldBankClient worldBankClient;

    public List<ResponseData> getIndicadorProbreza(String paisCode) {
        List<Object> response = null;

        try {
            response = worldBankClient.getIndicadorProbreza(paisCode);
        } catch (Exception e) {
            throw new TimeoutException("Tempo de resposta excedido / Verificar o código do país");
        }

        Gson gson = new Gson();
        String json = gson.toJson(response);
        List<ResponseData> responseData = gson.fromJson(json, List.class);
        return responseData;
    }


}

