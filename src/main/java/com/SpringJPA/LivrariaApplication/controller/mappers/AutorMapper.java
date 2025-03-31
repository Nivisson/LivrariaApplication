package com.SpringJPA.LivrariaApplication.controller.mappers;

import com.SpringJPA.LivrariaApplication.dto.AutorDTO;
import com.SpringJPA.LivrariaApplication.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Anotação do MapStruct para mapeamento automático
public interface AutorMapper {

    // Mapeamento dos campos, opcional se os campos tiverem o mesmo nome
    // Obrigatório em caso de nomes diferentes entre as entidades

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "dataNascimento", target = "dataNascimento")
    @Mapping(source = "nacionalidade", target = "nacionalidade")

    Autor toEntity(AutorDTO dto); // Converte um DTO em uma entidade Autor
    AutorDTO toDTO(Autor autor); //Converte uma entidade Autor em um DTO
}