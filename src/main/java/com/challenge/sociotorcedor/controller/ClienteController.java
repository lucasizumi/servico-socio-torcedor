package com.challenge.sociotorcedor.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.sociotorcedor.dto.ClienteDto;
import com.challenge.sociotorcedor.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@PostMapping(produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClienteDto> create(@RequestBody ClienteDto clienteDto) {
		ClienteDto cliente = this.service.cadastrar(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }
	
	@PutMapping(produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClienteDto> update(@RequestBody ClienteDto clienteDto) {
		ClienteDto cliente = this.service.atualizar(clienteDto);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.service.deletar(id);
        return ResponseEntity.ok().build();
    }
	
	  @GetMapping(value = "/list", produces = APPLICATION_JSON_UTF8_VALUE)
	    public ResponseEntity<List<ClienteDto>> findAll() {
	        List<ClienteDto> clienteDto = this.service.listar();
	        return ResponseEntity.status(HttpStatus.OK).body(clienteDto);
	    }
	
	@GetMapping(value = "/list/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClienteDto> findById(@PathVariable("id") Long id) {
		ClienteDto clienteDto = this.service.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(clienteDto);
    }
	
}
