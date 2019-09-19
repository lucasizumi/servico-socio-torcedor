package com.challenge.sociotorcedor.service.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.challenge.sociotorcedor.dto.ClienteDto;

@Component
public class ClienteProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMenssage(String queue, ClienteDto cliente) {
        jmsTemplate.convertAndSend(queue, cliente);
    }
	
}
