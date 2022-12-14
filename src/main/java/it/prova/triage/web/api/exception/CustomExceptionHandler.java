package it.prova.triage.web.api.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);
	}

	@ExceptionHandler(IdNotNullForInsertException.class)
	public ResponseEntity<Object> handleIdNotNullForInsertException(IdNotNullForInsertException ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.UNPROCESSABLE_ENTITY);

		return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(UtenteNotAuthorizedException.class)
	public ResponseEntity<Object> UtenteNotAuthorizedException(UtenteNotAuthorizedException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.UNAUTHORIZED);

		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UtenteNotFoundException.class)
	public ResponseEntity<Object> handleUtenteNotFoundException(UtenteNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PazienteNotFoundException.class)
	public ResponseEntity<Object> handlePazienteNotFoundException(PazienteNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PazienteNotDimessoException.class)
	public ResponseEntity<Object> handlePazienteNotDimessoException(PazienteNotDimessoException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.METHOD_NOT_ALLOWED);

		return new ResponseEntity<>(body, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(DottoreNotFoundException.class)
	public ResponseEntity<Object> handleDottoreNotFoundException(DottoreNotFoundException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DottoreNotInServizioException.class)
	public ResponseEntity<Object> handleDottoreNotInServizioException(DottoreNotInServizioException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.METHOD_NOT_ALLOWED);

		return new ResponseEntity<>(body, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(DottoreImpegnatoException.class)
	public ResponseEntity<Object> handleDottoreImpegnatoException(DottoreImpegnatoException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.METHOD_NOT_ALLOWED);

		return new ResponseEntity<>(body, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex, 
                HttpServletRequest request, HttpServletResponse response) {
        if (ex instanceof WebClientResponseException) {
        	Map<String, Object> body = new LinkedHashMap<>();
    		body.put("timestamp", LocalDateTime.now());
    		body.put("message", "ECCEZIONE LANCIATA DA DOTTORE");
    		body.put("status", HttpStatus.METHOD_NOT_ALLOWED);
        	
            return new ResponseEntity<>(body, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
 
}
