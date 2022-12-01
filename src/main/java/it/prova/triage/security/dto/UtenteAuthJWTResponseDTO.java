package it.prova.triage.security.dto;

import java.util.List;

import lombok.Data;

@Data
public class UtenteAuthJWTResponseDTO {

	private String token;
	private String type = "Bearer";
	private String username;
	private List<String> roles;

	public UtenteAuthJWTResponseDTO(String accessToken, String username, List<String> roles) {
		this.token = accessToken;
		this.username = username;
		this.roles = roles;
	}
}
