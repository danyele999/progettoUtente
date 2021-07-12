package it.daniele.ExeptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GestoreEccezioni extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CreationExeption.class)
	protected ResponseEntity<Object> handler(CreationExeption ex) {
		ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.PRECONDITION_REQUIRED);
		return buildResponseEntity(apiError);

	}

	@ExceptionHandler(DeleteExeption.class)
	protected ResponseEntity<Object> handler(DeleteExeption ex) {
		ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		return buildResponseEntity(apiError);

	}

	@ExceptionHandler(FindExeption.class)
	protected ResponseEntity<Object> handler(FindExeption ex) {
		ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		return buildResponseEntity(apiError);

	}

	@ExceptionHandler(ModifierExeption.class)
	protected ResponseEntity<Object> handler(ModifierExeption ex) {
		ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND);
		return buildResponseEntity(apiError);

	}

	@ExceptionHandler(ReadExeption.class)
	protected ResponseEntity<Object> handler(ReadExeption ex) {
		ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
		return buildResponseEntity(apiError);

	}

	private ResponseEntity<Object> buildResponseEntity(ApiError error) {
		return new ResponseEntity<>(error, error.getStatus());
	}

}
