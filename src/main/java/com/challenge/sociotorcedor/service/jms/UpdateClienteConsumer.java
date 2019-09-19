package com.challenge.sociotorcedor.service.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.challenge.sociotorcedor.dto.ClienteDto;
import com.challenge.sociotorcedor.service.AssociacaoClienteCampanhaService;
import com.challenge.sociotorcedor.service.ClienteService;

@Component
public class UpdateClienteConsumer {

	@Autowired
    private ClienteService clienteService;
	
    @Autowired
    private AssociacaoClienteCampanhaService service;
    
    private static final String UPDATE_QUEUE_NAME = "UpdateClienteCampanhaQueue";

    @JmsListener(destination = UPDATE_QUEUE_NAME, containerFactory = "myFactory")
    public void receiveMessage(ClienteDto clienteDto) {
        this.clienteService.finalizarCadastro(clienteDto);
        this.service.associarAtualizacaoClienteCampanha(clienteDto);
    }
}