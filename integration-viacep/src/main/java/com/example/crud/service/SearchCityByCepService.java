package com.example.crud.service;

import com.example.crud.domain.address.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchCityByCepService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public SearchCityByCepService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String SearchCityByCep(String cep){
        String url = "https://viacep.com.br/ws/{cep}/json/";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("cep", cep);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, uriVariables);

        try {
            Address address = objectMapper.readValue(response.getBody(), Address.class);
            return address.getLocalidade();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
