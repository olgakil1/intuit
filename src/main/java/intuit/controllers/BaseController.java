package intuit.controllers;


import javax.servlet.http.HttpServletResponse;

import intuit.exceptions.ApiError;
import intuit.exceptions.AuthorizationException;
import intuit.exceptions.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BaseController {

    private static final Logger log = LogManager.getLogger(BaseController.class);

    @ExceptionHandler( RuntimeException.class )
    private ResponseEntity<Object>  handleRuntimeException( final RuntimeException e, final HttpServletResponse response ) throws Throwable
    {
        log.error("Runtime Exception:" + e.getMessage());
        return new ResponseEntity<>(new ApiError( HttpStatus.INTERNAL_SERVER_ERROR ,e.getMessage(),RuntimeException.class.getName()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler( Exception.class )
    private ResponseEntity<Object>  handleException( final RuntimeException e, final HttpServletResponse response ) throws Throwable
    {
        log.error("Exception:" + e.getMessage());
        return new ResponseEntity<>(new ApiError( HttpStatus.INTERNAL_SERVER_ERROR ,e.getMessage(),Exception.class.getName()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthorizationException.class)
    private ResponseEntity<Object>  handleException( final IllegalStateException e, final HttpServletResponse response ) throws Throwable
    {
        log.error("Authorization Exception:" + e.getMessage());
        return new ResponseEntity<>(new ApiError( HttpStatus.UNAUTHORIZED ,e.getMessage(),AuthorizationException.class.getName()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<Object>  handleException(BadRequestException e)
    {
        log.error("Bad Request Exception:" + e.getMessage());
        return new ResponseEntity<>(new ApiError( HttpStatus.BAD_REQUEST ,e.getMessage(),BadRequestException.class.getName()), HttpStatus.BAD_REQUEST);
    }
}