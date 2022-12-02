package it.prova.triage.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.triage.dto.DottoreRequestDTO;
import it.prova.triage.dto.DottoreResponseDTO;
import it.prova.triage.dto.PazienteDTO;
import it.prova.triage.model.Paziente;
import it.prova.triage.model.StatoPaziente;
import it.prova.triage.service.PazienteService;
import it.prova.triage.web.api.exception.DottoreNotFoundException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/assegnaPaziente")
public class assegnaPazienteController {
	
	@Autowired
	private PazienteService service;

	@Autowired
	private WebClient webClient;

	@GetMapping("verificaDisponibilitaDottore/{codice}")
	public DottoreResponseDTO verificaDisponibilitaDottore(@PathVariable(value = "codice", required = true) String codice) {
		DottoreResponseDTO dottore = webClient.get().uri("/verifica/" + codice).retrieve().bodyToMono(DottoreResponseDTO.class).block();

		if (dottore == null)
			throw new DottoreNotFoundException("Dottore non trovato");

		return dottore;
	}
	
	@PostMapping("assegna")
	public PazienteDTO assegna(@RequestBody DottoreRequestDTO doc) {
		DottoreResponseDTO dottore = verificaDisponibilitaDottore(doc.getCodiceDottore());
	
		// assegno il dottore al paziente
		PazienteDTO paziente = PazienteDTO.buildPazienteDTOFromModel(service.findByCodiceFiscale(doc.getCodiceFiscale()));
		paziente.setCodiceDottore(doc.getCodiceDottore());
		
		DottoreResponseDTO test = new DottoreResponseDTO(dottore.getCodiceDottore(), doc.getCodiceFiscale());
		webClient.put().uri("/impostaInVisita").body(Mono.just(test), DottoreResponseDTO.class).retrieve().toEntity(DottoreResponseDTO.class).block();
		
		paziente.setStato(StatoPaziente.IN_VISITA);
		Paziente pazienteInstance = service.aggiorna(paziente.buildPazienteModel());
		return PazienteDTO.buildPazienteDTOFromModel(pazienteInstance);
	}
}
