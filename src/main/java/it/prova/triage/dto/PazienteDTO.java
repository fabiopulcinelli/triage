package it.prova.triage.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.triage.model.Paziente;
import it.prova.triage.model.StatoPaziente;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PazienteDTO {

	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;
	
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	
	@NotBlank(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;
	
	private Date dataRegistrazione;
	
	@Enumerated(EnumType.STRING)
	private StatoPaziente stato;

	//puo essere null se sono in attesa, ricoverato o dimesso
	private String codiceDottore;
	
	public PazienteDTO() {
	}
	
	public PazienteDTO(Long id, String nome, String cognome, String codiceFiscale, Date dataRegistrazione, StatoPaziente stato) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
	}
	
	public Paziente buildPazienteModel() {
		Paziente result = new Paziente(this.nome, this.cognome, this.codiceFiscale, this.dataRegistrazione, this.stato);
		result.setId(this.id);
			
		return result;
	}

	public static PazienteDTO buildPazienteDTOFromModel(Paziente pazienteModel) {
		PazienteDTO result = new PazienteDTO(pazienteModel.getId(), pazienteModel.getNome(), pazienteModel.getCognome(),
				pazienteModel.getCodiceFiscale(), pazienteModel.getDataRegistrazione(), pazienteModel.getStato());
		
		return result;
	}

	public static List<PazienteDTO> createPazienteDTOListFromModelList(List<Paziente> modelListInput) {
		return modelListInput.stream().map(pazienteEntity -> {
			
			return PazienteDTO.buildPazienteDTOFromModel(pazienteEntity);
		}).collect(Collectors.toList());
	}

	public static Set<PazienteDTO> createPazienteDTOSetFromModelSet(Set<Paziente> modelListInput) {
		return modelListInput.stream().map(pazienteEntity -> {
			return PazienteDTO.buildPazienteDTOFromModel(pazienteEntity);
		}).collect(Collectors.toSet());
	}
}
