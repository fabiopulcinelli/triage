package it.prova.triage.security.dto;

import java.util.List;

import lombok.Data;

@Data
public class UtenteInfoJWTResponseDTO {

	private String nome;
	private String cognome;
	private String type = "Bearer";
	private String username;
	private List<String> roles;

	public UtenteInfoJWTResponseDTO(String nome, String cognome, String username, List<String> roles) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.roles = roles;
	}
}