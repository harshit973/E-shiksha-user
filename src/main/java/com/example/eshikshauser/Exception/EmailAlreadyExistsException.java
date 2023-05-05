package com.example.eshikshauser.Exception;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends ServiceException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

}
