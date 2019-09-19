package com.challenge.sociotorcedor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.challenge.sociotorcedor.dto.CampanhaDto;

@Service
public class CampanhaService {

	@Autowired
    private RestTemplate restTemplate;
	
	@Value("${challenge.campanha.url}")
	private String url;
	
	private static final String URL_BUSCAR_POR_TIME = "/campanha/list/time/{time}";

	public List<CampanhaDto> buscarCampanhasPorTime(String time) {
        
		Map<String, String> params = new HashMap<>();
        params.put("time", time);
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat(URL_BUSCAR_POR_TIME));
        
        final ResponseEntity<List<CampanhaDto>> response = restTemplate.exchange(
                builder.buildAndExpand(params).toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CampanhaDto>>() {});
        
        return response.getBody();
    }
	
}
