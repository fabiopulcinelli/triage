package it.prova.triage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.triage.model.Paziente;
import it.prova.triage.paziente.repository.PazienteRepository;

@Service
public class PazienteServiceImpl implements PazienteService {
	@Autowired
	private PazienteRepository repository;

	public List<Paziente> listAllElements() {
		return (List<Paziente>) repository.findAll();
	}

	public Paziente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional
	public Paziente aggiorna(Paziente pazienteInstance) {
		return repository.save(pazienteInstance);
	}

	@Transactional
	public Paziente inserisciNuovo(Paziente pazienteInstance) {
		return repository.save(pazienteInstance);
	}

	@Transactional
	public void rimuovi(Paziente pazienteInstance) {
		repository.delete(pazienteInstance);
	}

	@Override
	public Paziente findByCodiceFiscale(String cfInput) {
		return repository.findByCodiceFiscale(cfInput);
	}

}
