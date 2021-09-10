package net.winnings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс ошибки, возникающей при обращении к несуществующему ресурсу
 * @author Nosolenko
 * @version 1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /** UID, необходимый для сериализации */
    private static final long serialVersionUID = 1L;

    /**
     * Процедура вывода сообщения об ошибке
     * @param message - сообщение ошибки
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
