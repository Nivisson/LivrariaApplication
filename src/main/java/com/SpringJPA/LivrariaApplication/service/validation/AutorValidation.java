package com.SpringJPA.LivrariaApplication.service.validation;

import com.SpringJPA.LivrariaApplication.exception.RegistroDuplicadoException;
import com.SpringJPA.LivrariaApplication.model.Autor;
import com.SpringJPA.LivrariaApplication.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@RequiredArgsConstructor // Utilizado para gerar o Construtor private final
@Component // Permite que seja chamada para validação de autores
public class AutorValidation {

    private final AutorRepository repository;

        public void validar(Autor autor){
        if(existeAutorCadastrado(autor)){ // Condicional se o autor está cadastrado
            throw new RegistroDuplicadoException("Autor já cadastrado!");
        }
    }

    private boolean existeAutorCadastrado(Autor autor){ // Busca o Autor no banco de dados
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()
        );

        if(autor.getId() == null){ // Condição para momento do cadastro, o ID ainda não foi gerado
            return autorEncontrado.isPresent() ;
        }
        // COndição abaixo utilizada para edição, para não duplicar o cadastro na edição
        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }
}
