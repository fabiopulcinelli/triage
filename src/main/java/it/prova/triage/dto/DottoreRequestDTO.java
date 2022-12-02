package it.prova.triage.dto;

import lombok.Data;

@Data
public class DottoreRequestDTO {
	private String codiceFiscale;
	private String codiceDottore;

	public DottoreRequestDTO() {
	}

	public DottoreRequestDTO(String codiceFiscale, String codiceDottore) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.codiceDottore = codiceDottore;
	}

}
