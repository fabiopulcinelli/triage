package it.prova.triage.web.api.exception;

public class PazienteNotDimessoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PazienteNotDimessoException(String message) {
		super(message);
	}

}