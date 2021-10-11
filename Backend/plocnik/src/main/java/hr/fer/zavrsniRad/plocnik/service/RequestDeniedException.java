package hr.fer.zavrsniRad.plocnik.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestDeniedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public RequestDeniedException(String message){super(message);}
}
