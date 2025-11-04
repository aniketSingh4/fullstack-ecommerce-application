package org.aniketSingh.userService.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailExistException  extends RuntimeException 
{
    public EmailExistException(String message) {
        super(message);
    }
}
