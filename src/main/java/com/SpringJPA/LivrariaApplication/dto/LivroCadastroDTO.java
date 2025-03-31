package com.SpringJPA.LivrariaApplication.dto;

import com.SpringJPA.LivrariaApplication.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

// @NotBlank valida campo obrigatório das Strings
// @NotNull valida campos obrigatórios dos demais tipos de variavel

public record LivroCadastroDTO(
        @ISBN // Verifica através do Spring se o código ISBN é valido
        @NotBlank(message = "campo obrigatorio")
        String isbn,

        @NotBlank(message = "campo obrigatorio")
        String titulo,

        @NotNull(message = "campo obrigatorio")
        @Past(message = "nao pode ser uma data futura") // Validação da data
        LocalDate dataPublicacao,

        @NotBlank(message = "campo obrigatorio")
        GeneroLivro genero,

        @NotNull(message = "campo obrigatorio")
        BigDecimal preco,

        @NotNull(message = "campo obrigatorio")
        UUID idAutor
) {
}