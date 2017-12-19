package fi.muni.cz.pa165.travelagency.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Radoslav Micko <445611>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The requested resource was not found.")
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor for ResourceNotFoundException
     */
    public ResourceNotFoundException() {
    }

    /**
     * Constructor for ResourceNotFoundException
     * @param message message to show
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor for ResourceNotFoundException
     * @param message message to show 
     * @param cause cause of exception
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
} 