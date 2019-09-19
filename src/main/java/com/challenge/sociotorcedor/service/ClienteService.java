package com.challenge.sociotorcedor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.sociotorcedor.dto.ClienteDto;
import com.challenge.sociotorcedor.model.Cliente;
import com.challenge.sociotorcedor.repository.ClienteRepository;
import com.challenge.sociotorcedor.service.jms.ClienteProducer;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private TimeService timeService;

	@Autowired
	private ClienteProducer producer;

	private static final String CREATE_QUEUE_NAME = "CreateClienteCampanhaQueue";

	private static final String UPDATE_QUEUE_NAME = "UpdateClienteCampanhaQueue";

	@Transactional
	public ClienteDto cadastrar(ClienteDto clienteDto) {

		Cliente clienteEncontrado = this.repository.buscarPorEmail(clienteDto.getEmail());
		if (clienteEncontrado != null) {
			throw new EntityExistsException("E-mail " + clienteDto.getEmail() + " já cadastrado.");
		}

		Cliente cliente = new Cliente();
		cliente.setNomeCompleto(clienteDto.getNomeCompleto());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setDataNascimento(clienteDto.getDataNascimento());

		repository.save(cliente);

		clienteDto.setId(cliente.getId());

		this.producer.sendMenssage(CREATE_QUEUE_NAME, clienteDto);
		return clienteDto;
	}

	public ClienteDto atualizar(ClienteDto clienteDto) {

		Cliente clienteEncontrado = this.repository.buscarPorEmail(clienteDto.getEmail());
		if (clienteEncontrado == null) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}

		Cliente cliente = clienteEncontrado;
		cliente.setNomeCompleto(clienteDto.getNomeCompleto());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setDataNascimento(clienteDto.getDataNascimento());

		repository.save(cliente);

		this.producer.sendMenssage(UPDATE_QUEUE_NAME, clienteDto);
		return clienteDto;
	}

	public ResponseEntity<?> deletar(Long id) {
		this.repository.deleteById(id);
        return ResponseEntity.ok().build();
	}

	@Transactional(readOnly = true)
	public List<ClienteDto> listar() {
		List<Cliente> clientes = this.repository.findAll();

		List<ClienteDto> clientesDto = new ArrayList<>();
		for (Cliente cliente : clientes) {
			ClienteDto clienteDto = new ClienteDto();
			clienteDto.setId(cliente.getId());
			clienteDto.setEmail(cliente.getEmail());
			clienteDto.setNomeCompleto(cliente.getNomeCompleto());
			clienteDto.setDataNascimento(cliente.getDataNascimento());
			clienteDto.setIdTime(cliente.getIdTime());
			clienteDto.setTime(timeService.buscarPorId(cliente.getIdTime()).getNome());
			clienteDto.setCampanhas(cliente.getCampanhas());
			clientesDto.add(clienteDto);
		}

		return clientesDto;
	}

	@Transactional(readOnly = true)
	public ClienteDto buscarPorId(Long id) {

		Optional<Cliente> clienteEncontrado = this.repository.findById(id);
		if (!clienteEncontrado.isPresent()) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}

		Cliente cliente = clienteEncontrado.get();
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(cliente.getId());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setNomeCompleto(cliente.getNomeCompleto());
		clienteDto.setDataNascimento(cliente.getDataNascimento());
		clienteDto.setIdTime(cliente.getIdTime());
		clienteDto.setTime(timeService.buscarPorId(cliente.getIdTime()).getNome());
		clienteDto.setCampanhas(cliente.getCampanhas());

		return clienteDto;
	}

	public void atualizarClienteCampanha(ClienteDto clienteDto) {
		Cliente clienteEncontrado = this.repository.buscarPorEmail(clienteDto.getEmail());
		if (clienteEncontrado == null) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}

		Cliente cliente = clienteEncontrado;
		cliente.getCampanhas().clear();
		cliente.setCampanhas(clienteDto.getCampanhas());

		repository.save(cliente);
	}

	public void finalizarCadastro(ClienteDto clienteDto) {
		Cliente clienteEncontrado = this.repository.buscarPorEmail(clienteDto.getEmail());
		if (clienteEncontrado == null) {
			throw new EntityNotFoundException("Cliente não encontrado.");
		}

		Cliente cliente = clienteEncontrado;
		cliente.setIdTime(timeService.buscarPorNome(clienteDto.getTime()).getId());

		this.repository.save(cliente);
	}

}
