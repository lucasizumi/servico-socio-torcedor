package com.challenge.sociotorcedor.service.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.challenge.sociotorcedor.dto.ClienteDto;
import com.challenge.sociotorcedor.service.AssociacaoClienteCampanhaService;
import com.challenge.sociotorcedor.service.ClienteService;

@Component
public class CreateClienteConsumer {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AssociacaoClienteCampanhaService associacaoClienteCampanhaservice;
    
    private static final String CREATE_QUEUE_NAME = "CreateClienteCampanhaQueue";

    @JmsListener(destination = CREATE_QUEUE_NAME , containerFactory = "myFactory")
    public void receiveMessage(ClienteDto clienteDto) {
        this.clienteService.finalizarCadastro(clienteDto);
        this.associacaoClienteCampanhaservice.associarCadastroClienteCampanha(clienteDto);
    }
}