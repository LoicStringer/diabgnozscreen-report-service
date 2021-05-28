package com.diabgnozscreenreportservice.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;

@RestControllerAdvice
public class ExceptionsHandlerCenter extends ResponseEntityExceptionHandler{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(Exception.class)
	public ResponseStatusException handleException(Exception ex) {
		log.error("Unhandled exception has been raised :"+ ex);
		return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
				"An unpredicted exception occured.",ex.getCause());
	}
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ExceptionResponse> handleFeignException(FeignException fEx) {
		ExceptionResponse exceptionResponse = feignExceptionResponseBuild(fEx);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.valueOf(fEx.status()));
	}
	
	private ExceptionResponse feignExceptionResponseBuild(FeignException fEx) {
		String statusCode = HttpStatus.valueOf(fEx.status()).toString();
		String exceptionMessage = fEx.contentUTF8();
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), statusCode,
				fEx.getClass().getCanonicalName(), exceptionMessage);
		return exceptionResponse;
	}
	
}
