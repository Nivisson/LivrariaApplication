package com.SpringJPA.LivrariaApplication.controller;

import com.SpringJPA.LivrariaApplication.controller.mappers.UsuarioMapper;
import com.SpringJPA.LivrariaApplication.dto.UsuarioCadastroDTO;
import com.SpringJPA.LivrariaApplication.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor // CONTRUINDO CONSTRUTORES 'PRIVATE FINAL'
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // RESPOSTA DE STATUS 201
    public void salvar(@RequestBody @Valid UsuarioCadastroDTO dto){
        var usuario = mapper.toEntity(dto);
        service.salvar(usuario);
    }
}
