package com.Guilherme.bffagendadortarefas.infrastructure.client.config;

import com.Guilherme.bffagendadortarefas.infrastructure.excpetions.BusinessException;
import com.Guilherme.bffagendadortarefas.infrastructure.excpetions.ConflictException;
import com.Guilherme.bffagendadortarefas.infrastructure.excpetions.IllegalArgumentException;
import com.Guilherme.bffagendadortarefas.infrastructure.excpetions.ResourceNotFoundException;
import com.Guilherme.bffagendadortarefas.infrastructure.excpetions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {



    @Override
    public Exception decode(String s, Response response) {

        String mensagemError = mensagemError(response);

        switch (response.status()){
            case 409:
                return new ConflictException("Erro: "+mensagemError);
            case 403:
                return new ResourceNotFoundException("Erro: "+mensagemError);
            case 401:
                return new UnauthorizedException("Erro: "+mensagemError);
            case 400:
                return new IllegalArgumentException("Erro: "+mensagemError);
            default:
                return new BusinessException("Erro: "+mensagemError);

        }
    }

    private String mensagemError(Response response){
        try {
            if(Objects.isNull(response.body())){
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
