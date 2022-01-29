package br.com.fiap.epictask.exceptions;


import br.com.fiap.epictask.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {


    //TRATANDO ERROS DE REGRAS DE NEGÓCIO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        List<String> listErr = new ArrayList<>();

        //Criando o objeto de resposta de erro
        ApiError erro = new ApiError(HttpStatus.BAD_REQUEST);

        //Adicionando cada um dos erros ao objeto
        for (ObjectError err : errors) {
            listErr.add(err.getDefaultMessage());
        }
        erro.setErros(listErr);
        return ResponseEntity.badRequest().body(erro);
    }

    //Tratando exceções de erro de sintax do JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handlerException() {
        ApiError erro = new ApiError(HttpStatus.BAD_REQUEST, "JSON SYNTAX ERROR");
        return ResponseEntity.badRequest().body(erro);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        System.out.println(e.getClass().getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
