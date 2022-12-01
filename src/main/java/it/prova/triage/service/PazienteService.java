package it.prova.triage.service;

import java.util.List;

import it.prova.triage.model.Paziente;;

public interface PazienteService {
	List<Paziente> listAllElements();

	Paziente caricaSingoloElemento(Long id);

	Paziente aggiorna(Paziente pazienteInstance);

	Paziente inserisciNuovo(Paziente pazienteInstance);

	void rimuovi(Paziente pazienteInstance);

	Paziente findByCodiceFiscale(String cfInput);
}
