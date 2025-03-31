package com.SpringJPA.LivrariaApplication.controller.mappers;

import com.SpringJPA.LivrariaApplication.dto.LivroCadastroDTO;
import com.SpringJPA.LivrariaApplication.dto.LivroPesquisaDTO;
import com.SpringJPA.LivrariaApplication.model.Livro;
import com.SpringJPA.LivrariaApplication.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

// Anotação mapper para mapeamento automático entre as classes / chamar mapeamento do AutorMapper
@Mapper(componentModel = "spring", uses = AutorMapper.class )
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    // Mapeando o autor com base no ID do DTO
    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")

    @Mapping(source = "isbn", target = "isbn")
    @Mapping(source = "titulo", target = "titulo")
    @Mapping(source = "dataPublicacao", target = "dataPublicacao")
    @Mapping(source = "genero", target = "genero")
    @Mapping(source = "preco", target = "preco")
    // Abaixo os métodos são para mapeamento da Entity Livro com DTO de Livro
    // Não foi utilizado @Mapping, pois os nomes dos campos são iguais
    public abstract Livro toEntity(LivroCadastroDTO dto);
    public abstract LivroPesquisaDTO toDTO(Livro livro);
}
