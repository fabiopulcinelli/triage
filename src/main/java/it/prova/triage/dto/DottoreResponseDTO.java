package it.prova.triage.dto;

import lombok.Data;

@Data
public class DottoreResponseDTO {
	private String codiceDottore;
	private String nome;
	private String cognome;
	private Boolean inServizio;
	private Boolean inVisita;
	private String codiceFiscale;

	public DottoreResponseDTO() {
	}

	public DottoreResponseDTO(String codiceDottore, String nome, String cognome, Boolean inServizio, Boolean inVisita) {
		super();
		this.codiceDottore = codiceDottore;
		this.nome = nome;
		this.cognome = cognome;
		this.inServizio = inServizio;
		this.inVisita = inVisita;
	}
	
	public DottoreResponseDTO(String codiceDottore, String codiceFiscale) {
		this.codiceDottore = codiceDottore;
		this.codiceFiscale = codiceFiscale;
	}

}
