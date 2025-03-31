package com.SpringJPA.LivrariaApplication.service.validation;

import com.SpringJPA.LivrariaApplication.exception.CampoInvalidoException;
import com.SpringJPA.LivrariaApplication.exception.RegistroDuplicadoException;
import com.SpringJPA.LivrariaApplication.model.Livro;
import com.SpringJPA.LivrariaApplication.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidation {

    private static final int ANO_EXIGENCIA_PRECO = 2020; // DEFINE ANO PARA EXIGENCIA DE PREÇO

    private final LivroRepository repository;

    public void validar(Livro livro){
        if(existeLivroComIsbn(livro)){ // Verifica se tem ISBN já cadastrado
            throw new RegistroDuplicadoException("ISBN já cadastrado!");
        }

        if(isPrecoObrigatorioNulo(livro)){
            throw new CampoInvalidoException("preco", "Para livros com ano de publicação a partir de 2020, o preço é obrigatório.");
        }
    }

    private boolean existeLivroComIsbn(Livro livro){ // Procura ISBN no banco de dados
        Optional<Livro> livroEncontrado = repository.findByIsbn(livro.getIsbn());

        if(livro.getId() == null){ // CONDIÇÃO DE CADASTRO
            return livroEncontrado.isPresent();
        }

        return livroEncontrado // Verificação se tem algum livro cadastrado, mas com ID diferente
                .map(Livro::getId)
                .stream()
                .anyMatch(id -> !id.equals(livro.getId()));
    }

    private boolean isPrecoObrigatorioNulo(Livro livro) {
        return livro.getPreco() == null &&
                livro.getDataPublicacao().getYear() >= ANO_EXIGENCIA_PRECO;
    }
}
