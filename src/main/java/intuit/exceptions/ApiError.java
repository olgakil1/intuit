package intuit.exceptions;

import org.springframework.http.HttpStatus;

public class ApiError {

    public String status;
    public int code;    
    public Object message;
    public String exception;
    
    public ApiError(HttpStatus status, Object message, String exception) {
    
        this.status = status.name();
        this.code = status.value();
        this.message = message;
        this.exception = exception;
    } 
 }