package com.SpringJPA.LivrariaApplication.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.UUID;

public interface GenericController {

    default URI gerarHeaderLocation(UUID id){ // GERA URL COM ID APOS OPERACAO (SALVAR/ATUALIZAR)
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}