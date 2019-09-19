package com.challenge.sociotorcedor.dto;

import java.time.LocalDate;

public class CampanhaDto {

	private Long id;

	private String nome;

	private String time;

	private LocalDate dataInicio;

	private LocalDate dataFim;

	public CampanhaDto() {
		super();
	}

	public CampanhaDto(String nome, String time, LocalDate dataInicio, LocalDate dataFim) {
		super();
		this.nome = nome;
		this.time = time;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

}
