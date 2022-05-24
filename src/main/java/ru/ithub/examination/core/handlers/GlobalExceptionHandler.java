package ru.ithub.examination.core.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ithub.examination.core.exceptions.AccessDeniedException;
import ru.ithub.examination.core.exceptions.AlreadyExistException;
import ru.ithub.examination.core.exceptions.AuthenticationException;
import ru.ithub.examination.core.exceptions.EPException;
import ru.ithub.examination.core.exceptions.NotFoundException;
import ru.ithub.examination.payload.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    private final Logger logger = LogManager.getLogger();

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleBadCredentialsException(HttpServletRequest request, BadCredentialsException ex) {
        logger.error(ex.getClass().getSimpleName() + " {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.of(List.of(ex.getMessage()), HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleNotFoundException(HttpServletRequest request, NotFoundException ex) {
        logger.error(ex.getClass().getSimpleName() + " {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.of(List.of(ex.getMessage()), HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleAuthenticationException(HttpServletRequest request, AuthenticationException ex) {
        logger.error(ex.getClass().getSimpleName() + " {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.of(List.of(ex.getMessage()), HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(EPException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleIChatException(HttpServletRequest request, EPException ex) {
        logger.error(ex.getClass().getSimpleName() + " {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.of(List.of(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException ex) {
        logger.error(ex.getClass().getSimpleName() + " {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.of(List.of(ex.getMessage()), HttpStatus.FORBIDDEN.value(), ex.getMessage()));
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleAlreadyExistException(HttpServletRequest request, AlreadyExistException ex) {
        logger.error(ex.getClass().getSimpleName() + " {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.of(List.of(ex.getMessage()), HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
}
