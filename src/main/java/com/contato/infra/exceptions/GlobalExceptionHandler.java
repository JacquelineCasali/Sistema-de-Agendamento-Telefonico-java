package com.contato.infra.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultiplasRegrasException.class)
    public ResponseEntity<?> handleMultiplasRegras(MultiplasRegrasException ex) {
        return ResponseEntity.badRequest().body(Map.of("erros", ex.getMensagens()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var mensagens = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(Map.of("erros", mensagens));
    }

    @ExceptionHandler({
            ContatoNaoEncontradoException.class,
            RegraNegocioException.class,

    })
    public ResponseEntity<?> handlePersonalizadas(RuntimeException ex) {
        return ResponseEntity.badRequest().body(Map.of("erros", List.of(ex.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOutras(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("erros", List.of("Erro interno: " + ex.getMessage())));
    }
}
