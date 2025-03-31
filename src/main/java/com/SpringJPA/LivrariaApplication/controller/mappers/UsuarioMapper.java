package com.SpringJPA.LivrariaApplication.controller.mappers;

import com.SpringJPA.LivrariaApplication.dto.UsuarioCadastroDTO;
import com.SpringJPA.LivrariaApplication.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Mapeamento Usuario / UsuarioDTO
    // Não foi utilizado @Mapping, pois os nomes dos campos são iguais
    Usuario toEntity(UsuarioCadastroDTO dto);
}
