package com.SpringJPA.LivrariaApplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

// @NotBlank valida campo obrigatório das Strings
// @NotNull valida campos obrigatórios dos demais tipos de variavel
public record UsuarioCadastroDTO(
        @NotBlank(message = "campo obrigatorio")
        String login,

        @Email(message = "inválido")
        @NotBlank(message = "campo obrigatorio")
        String email,

        @NotBlank(message = "campo obrigatorio")
        String senha,

        List<String> roles
) {
}
