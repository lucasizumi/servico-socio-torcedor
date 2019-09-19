package com.challenge.sociotorcedor.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TB_CLIENTE")
@SequenceGenerator(name = "seqCliente", sequenceName = "SQ_CLIENTE")
public class Cliente {

	@Id
	@GeneratedValue(generator = "seqCliente", strategy = GenerationType.SEQUENCE)
	@Column(name = "cd_cliente", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nm_cliente", nullable = false)
    private String nomeCompleto;

	@Column(name = "dt_nascimento", nullable = false)	
    private LocalDate dataNascimento;
    
	@Column(name = "ds_email", nullable = false)
    private String email;

	@Column(name = "cd_time")
    private Long idTime;

	@ElementCollection
    @CollectionTable(name = "TB_CLIENTE_CAMPANHA", joinColumns = @JoinColumn(name = "cd_cliente", nullable = false))
    private List<Long> campanhas = new ArrayList<>();

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
