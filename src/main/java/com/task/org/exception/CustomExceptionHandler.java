package com.task.org.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.authorization.AuthorizationDeniedException;


@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ProblemDetail> handleNullException(NullPointerException ex){
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        errorDetail.setProperty("access_denied_reason","Null value was passed.");
        return new ResponseEntity<>(errorDetail, HttpStatusCode.valueOf(errorDetail.getStatus()));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ProblemDetail> handleBadCredentialException(BadCredentialsException ex){
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        errorDetail.setProperty("access_denied_reason","Authentication Failed Bad Credentials.");
        return new ResponseEntity<>(errorDetail, HttpStatusCode.valueOf(errorDetail.getStatus()));
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ProblemDetail> handleAccessDeniedException(AccessDeniedException ex){
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN,ex.getMessage());
        errorDetail.setProperty("access_denied_reason","Not Authorized.");
        return new ResponseEntity<>(errorDetail, HttpStatusCode.valueOf(errorDetail.getStatus()));
    }
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ProblemDetail> handleSecurityException(SecurityException ex){
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        errorDetail.setProperty("access_denied_reason", "JWT Signature not valid !");
        return new ResponseEntity<>(errorDetail, HttpStatusCode.valueOf(errorDetail.getStatus()));
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ProblemDetail> handleExpiredJwtException(ExpiredJwtException ex){
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        errorDetail.setProperty("access_denied_reason", "JWT token already expired");
        return new ResponseEntity<>(errorDetail, HttpStatusCode.valueOf(errorDetail.getStatus()));
    }
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ProblemDetail> handleAuthorizationDeniedException(AuthorizationDeniedException ex){
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        errorDetail.setProperty("access_denied_reason", "Authorization Failed !");
        return new ResponseEntity<>(errorDetail, HttpStatusCode.valueOf(errorDetail.getStatus()));
    }


}
