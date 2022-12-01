package it.prova.triage.paziente.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.triage.model.Paziente;

public interface PazienteRepository extends CrudRepository<Paziente, Long> {
	Paziente findByCodiceFiscale(String cfInput);
}
