package com.challenge.sociotorcedor.dto;

import java.time.LocalDate;
import java.util.List;

public class ClienteDto {

	private Long id;

	private String nomeCompleto;

	private String email;

	private LocalDate dataNascimento;

	private String time;

	private Long idTime;

	private List<Long> campanhas;

	public ClienteDto() {
		super();
	}

	public ClienteDto(String nomeCompleto, String email, LocalDate dataNascimento, String time, Long idTime,
			List<Long> campanhas) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.time = time;
		this.idTime = idTime;
		this.campanhas = campanhas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Long getIdTime() {
		return idTime;
	}

	public void setIdTime(Long idTime) {
		this.idTime = idTime;
	}

	public List<Long> getCampanhas() {
		return campanhas;
	}

	public void setCampanhas(List<Long> campanhas) {
		this.campanhas = campanhas;
	}

}
