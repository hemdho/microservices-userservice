package com.omniri.assignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.mail.MailAuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.omniri.assignment.dto.GenericResponse;

@ControllerAdvice(basePackages = {"com.omniri.assignment"})
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler  {
	
	  @Autowired
	    private MessageSource messages;
	  
	public RestResponseEntityExceptionHandler() {
   	 super();
   	System.out.println(" Created rest response entity exception handler");
      
   }
	
	
	 @ExceptionHandler({org.springframework.security.access.AccessDeniedException.class})
	    protected ResponseEntity<Object> handleAccessDeniedException(final RuntimeException ex, final WebRequest request){
	    	  logger.error("400 Status Code", ex);
	    	  final GenericResponse bodyOfResponse = new GenericResponse("Access denied exception", "UnAtuhorised Access");
	    	  return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	    }
	    
	    @ExceptionHandler({IllegalArgumentException.class})
	    protected ResponseEntity<Object> handleIllegalArgumentException(final RuntimeException ex,final WebRequest request){
	    	logger.error("400 Status Code", ex);
	    	 final GenericResponse bodyOfResponse = new GenericResponse("Illegal Argument Exception", "");
	   	  return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }
	    
	    // 400
	    @Override
	    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
	        logger.error("400 Status Code", ex);
	        final BindingResult result = ex.getBindingResult();
	        final GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(), "Invalid" + result.getObjectName());
	        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }

	    @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
	        logger.error("400 Status Code", ex);
	        final BindingResult result = ex.getBindingResult();
	        final GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(), "Invalid" + result.getObjectName());
	        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }

	    
	    
	    @ExceptionHandler({BadCredentialsException.class})
	    public ResponseEntity<Object> handleBadCredentials(final RuntimeException ex,final WebRequest request){
	    	logger.error("401 Status Code-----------", ex);
	        final GenericResponse bodyOfResponse = new GenericResponse("bad credentials", "InternalError");
	        return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	    }
	    @ExceptionHandler({org.springframework.security.authentication.DisabledException.class})
	    public ResponseEntity<Object> handleDisabledUserException(final RuntimeException ex,final WebRequest request){
	    	logger.error("401 Status Code-----------", ex);
	        final GenericResponse bodyOfResponse = new GenericResponse("bad credentials", "InternalError");
	        return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	    }
	    
	    @ExceptionHandler({ Exception.class })
	    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
	        logger.error("500 Status Code", ex);
	        
	        final GenericResponse bodyOfResponse = new GenericResponse(" Internal error  " + ex.getLocalizedMessage(), "InternalError");
	        return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}