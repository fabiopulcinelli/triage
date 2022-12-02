package it.prova.triage.web.api.exception;

public class DottoreNotInServizioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DottoreNotInServizioException(String message) {
		super(message);
	}
}