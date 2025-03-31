package com.SpringJPA.LivrariaApplication.dto;

import com.SpringJPA.LivrariaApplication.model.GeneroLivro;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroPesquisaDTO(
        UUID id,
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        AutorDTO autor
) {
}
