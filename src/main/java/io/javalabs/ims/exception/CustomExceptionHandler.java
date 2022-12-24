package io.javalabs.ims.exception;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.javalabs.ims.dto.ExceptionResponseDTO;


@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		ExceptionResponseDTO exceptionResponseEntity = new ExceptionResponseDTO(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponseEntity, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public final ResponseEntity<Object> handleEmptyResultDataAccessException(Exception ex, WebRequest request){
		ExceptionResponseDTO exceptionResponseEntity = new ExceptionResponseDTO(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<>();
		for (final FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errors.add("Validation Error found in " + fieldError.getField() + " : " + fieldError.getDefaultMessage());
		}

		for (final ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
			errors.add(objectError.getObjectName() + " : " + objectError.getDefaultMessage());
		}
		ExceptionResponseDTO exceptionResponseEntity = new ExceptionResponseDTO(LocalDateTime.now(), "Validation Failed",
				errors.toString());
		return new ResponseEntity<Object>(exceptionResponseEntity, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex, WebRequest request)
			throws IOException {
		ExceptionResponseDTO exceptionResponseEntity = new ExceptionResponseDTO(LocalDateTime.now(), "Invalid Request",
				ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponseEntity, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> accessDenidException(AccessDeniedException ex, WebRequest request) {
		ExceptionResponseDTO exceptionResponseEntity = new ExceptionResponseDTO(LocalDateTime.now(),
				"Authorization Failed", ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponseEntity, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public void GenericException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
}
