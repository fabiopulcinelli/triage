package it.prova.triage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "paziente")
@Data
public class Paziente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "codiceFiscale")
	private String codiceFiscale;
	@Column(name = "dataRegistrazione")
	private Date dataRegistrazione;
	
	@Enumerated(EnumType.STRING)
	private StatoPaziente stato;

	public Paziente() {
	}

	public Paziente(String nome, String cognome, String codiceFiscale, Date dataRegistrazione, StatoPaziente stato) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
	}

	public Paziente(Long id) {
		super();
		this.id = id;
	}
}
