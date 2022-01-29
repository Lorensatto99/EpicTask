package br.com.fiap.epictask.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class ApiError {

    private Date timestep;
    private HttpStatus status;
    private List<String> erros;

    public ApiError(HttpStatus status) {
        this.timestep = new Date();
        this.status = status;
    }

    public ApiError(HttpStatus status, String message) {
        this.timestep = new Date();
        this.status = status;
        erros = Arrays.asList(message);
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
