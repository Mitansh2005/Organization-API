package com.task.org.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex){
        ProblemDetail errorDetail = null;
        if(ex instanceof BadCredentialsException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED,ex.getMessage());
            errorDetail.setProperty("access_denied_reason","Authentication Failed Bad Credentials");
        }
        if(ex instanceof AccessDeniedException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
            errorDetail.setProperty("access_denied_reason","Not Authorized !");
        }
        if(ex instanceof SignatureException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
            errorDetail.setProperty("access_denied_reason","JWT Signature not valid !");
        }
        if (ex instanceof ExpiredJwtException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN,ex.getMessage());
            errorDetail.setProperty("access_denied_reason","JWT token already expired");
        }
        if (ex instanceof AuthorizationDeniedException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN,ex.getMessage());
            errorDetail.setProperty("access_denied_reason","Authorization Failed !");
        }

        return errorDetail;
    }
}
