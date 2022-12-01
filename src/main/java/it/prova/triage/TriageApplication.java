package it.prova.triage;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.triage.model.Paziente;
import it.prova.triage.model.Ruolo;
import it.prova.triage.model.Utente;
import it.prova.triage.service.PazienteService;
import it.prova.triage.service.RuoloService;
import it.prova.triage.service.UtenteService;

@SpringBootApplication
public class TriageApplication implements CommandLineRunner {
	
	private static final Logger LOGGER = LogManager.getLogger(TriageApplication.class);

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	
	@Autowired
	private PazienteService pazienteService;

	public static void main(String[] args) {
		SpringApplication.run(TriageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Inizializzo db

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Sub Operator", Ruolo.ROLE_SUB_OPERATOR) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Sub Operator", Ruolo.ROLE_SUB_OPERATOR));
		}

		// a differenza degli altri progetti cerco solo per username perche' se vado
		// anche per password ogni volta ne inserisce uno nuovo, inoltre l'encode della
		// password non lo
		// faccio qui perche gia lo fa il service di utente, durante inserisciNuovo
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("user") == null) {
			Utente classicUser = new Utente("user", "user", "Antonio", "Verdi", new Date());
			classicUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", Ruolo.ROLE_SUB_OPERATOR));
			utenteServiceInstance.inserisciNuovo(classicUser);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser.getId());
		}

		if (utenteServiceInstance.findByUsername("special") == null) {
			Utente specialUser = new Utente("special", "special", "Antonioo", "Verdii", new Date());
			specialUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Special User", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(specialUser);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(specialUser.getId());
		}

		if (utenteServiceInstance.findByUsername("user2") == null) {
			Utente classicUser2 = new Utente("user2", "user2", "Antoniooo", "Verdiii", new Date());
			classicUser2.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", Ruolo.ROLE_SUB_OPERATOR));
			utenteServiceInstance.inserisciNuovo(classicUser2);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(classicUser2.getId());
		}
		
		pazienteService.inserisciNuovo(new Paziente("Mario", "Rossi", "MARROSS78P13H501F", new SimpleDateFormat("dd/MM/yyyy").parse("13/12/1978")));
		pazienteService.inserisciNuovo(new Paziente("Peppe", "Bianchi","PPPBBB58P13H501F", new SimpleDateFormat("dd/MM/yyyy").parse("01/10/1958")));
		pazienteService.inserisciNuovo(new Paziente("Antonio", "Marrone", "ANTMAR88P13H501F", new SimpleDateFormat("dd/MM/yyyy").parse("22/02/1988")));
		pazienteService.inserisciNuovo(new Paziente("Ottavio", "Malta", "OTTMAL62P13H501F", new SimpleDateFormat("dd/MM/yyyy").parse("11/04/1962")));
		
		LOGGER.info("...Elenco tutti......");
		pazienteService.listAllElements().forEach(d -> LOGGER.info(d.getCodiceFiscale()));
		LOGGER.info("...fine......");

	}
}
