package br.com.emissaocartao.apispring.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartaoException extends RuntimeException {
    public CartaoException(String message) {
        super(message);
    }
}
