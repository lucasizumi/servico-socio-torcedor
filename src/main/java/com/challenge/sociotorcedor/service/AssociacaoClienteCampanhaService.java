package com.challenge.sociotorcedor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.challenge.sociotorcedor.dto.CampanhaDto;
import com.challenge.sociotorcedor.dto.ClienteDto;

@Component
public class AssociacaoClienteCampanhaService {

	@Autowired
    private ClienteService clienteService;
	
	@Autowired
    private CampanhaService campanhaService;
	
	public void associarCadastroClienteCampanha(ClienteDto clienteDto) {
        List<CampanhaDto> campanhasEncontradas = campanhaService.buscarCampanhasPorTime(clienteDto.getTime());
        if (!campanhasEncontradas.isEmpty()) {
            clienteDto.setCampanhas(campanhasEncontradas.stream().map(c -> c.getId()).collect(Collectors.toList()));
            clienteService.atualizarClienteCampanha(clienteDto);
        }
    }

    public void associarAtualizacaoClienteCampanha(ClienteDto clienteDto) {
        List<CampanhaDto> campanhasEncontradas = campanhaService.buscarCampanhasPorTime(clienteDto.getTime());
        
        if (!campanhasEncontradas.isEmpty()) {
            List<Long> campanhas = campanhasEncontradas.stream().map(c -> c.getId()).collect(Collectors.toList());
            for (Long id : campanhas) {
                if (!clienteDto.getCampanhas().contains(id)) {
                    clienteDto.getCampanhas().add(id);
                }
            }
            clienteService.atualizarClienteCampanha(clienteDto);
        }
    }
	
}
