package com.challenge.sociotorcedor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.challenge.sociotorcedor.dto.TimeDto;

@Service
public class TimeService {

	@Autowired
    private RestTemplate restTemplate;

    @Value("${challenge.campanha.url}")
    private String url;

    private static final String URL_TIME_POR_ID = "/time/list/id/{id}";
    
    private static final String URL_TIME_POR_NOME = "/time/list/nome/{nome}";

    public TimeDto buscarPorNome(String nome) {
        try {
            ResponseEntity<TimeDto> response = restTemplate.getForEntity(url.concat(URL_TIME_POR_NOME), TimeDto.class, nome);
            return response.getBody();
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                throw new RuntimeException("O time com o nome " + nome + " não foi encontrado.", exception);
            } else {
                throw exception;
            }
        }
    }

    public TimeDto buscarPorId(Long id) {
        try {
            ResponseEntity<TimeDto> response = restTemplate.getForEntity(url.concat(URL_TIME_POR_ID), TimeDto.class, id);
            return response.getBody();
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
            	throw new RuntimeException("O time com o id " + id + " não foi encontrado.", exception);
            } else {
                throw exception;
            }
        }
    }
    
}
