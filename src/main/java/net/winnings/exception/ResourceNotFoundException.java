package net.winnings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class of the error that occurs when accessing a nonexistent resource
 * @author Nosolenko
 * @version 1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /** UID required for serialization */
    private static final long serialVersionUID = 1L;

    /**
     * Error message display procedure
     * @param message - error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
