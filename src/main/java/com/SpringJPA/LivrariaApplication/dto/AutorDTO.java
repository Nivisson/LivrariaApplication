package com.SpringJPA.LivrariaApplication.dto;

import com.SpringJPA.LivrariaApplication.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

// CAMPOS QUE SÃO UTILIZADOS NA VIEW PARA CADASTRO
public record AutorDTO(
        UUID id,

        @NotBlank(message = "campo obrigatorio")
        @Size(min = 2, max = 100, message = "campo fora do tamanho padrao")
        String nome,

        @NotNull(message = "campo obrigatorio")
        @Past(message = "nao pode ser uma data futura")
        LocalDate dataNascimento,

        @NotBlank(message = "campo obrigatorio")
        @Size(max = 50, min = 2, message = "campo fora do tamanho padrao")
        String nacionalidade) {
}
