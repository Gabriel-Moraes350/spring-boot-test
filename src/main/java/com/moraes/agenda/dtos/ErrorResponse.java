package com.moraes.agenda.dtos;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private String message;
    private int status;
    private List<String> erros = new ArrayList<>();

    public ErrorResponse() {
    }

    public ErrorResponse(String message, int status, List<String> erros) {
        this.message = message;
        this.status = status;
        this.erros = erros;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    public void addErro(String erro){
        this.erros.add(erro);
    }
}
