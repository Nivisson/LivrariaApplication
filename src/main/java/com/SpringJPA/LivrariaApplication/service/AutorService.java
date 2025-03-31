package com.SpringJPA.LivrariaApplication.service;

import com.SpringJPA.LivrariaApplication.model.Autor;
import com.SpringJPA.LivrariaApplication.model.Usuario;
import com.SpringJPA.LivrariaApplication.repository.AutorRepository;
import com.SpringJPA.LivrariaApplication.repository.LivroRepository;
import com.SpringJPA.LivrariaApplication.service.customSecurity.SecurityService;
import com.SpringJPA.LivrariaApplication.service.validation.AutorValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor // CRIANDO CONSTRUTORES PRIVATE FINAL (ANOTAÇÃO LOMBOK)
public class AutorService {

    private final AutorRepository repository;
    private final AutorValidation validador;
    private final LivroRepository livroRepository;
    private final SecurityService securityService;

    public Autor salvar(Autor autor){// Salvar novo Autor
        validador.validar(autor); // Chama as regras de validação do autor
        Usuario usuario = securityService.obterUsuarioLogado();
        autor.setUsuario(usuario);
        return repository.save(autor);
    }

    public void atualizar(Autor autor){ // Atualiza dados do Autor
        if(autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar, o autor precisa estar cadastrado");
        }
        validador.validar(autor); // Chama as regras de validação do autor
        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){ // Obtem autor pelo ID
        return repository.findById(id);
    }

    // PESQUISA DE AUTORES UTILIZANDO EXAMPLE
    public List<Autor> pesquisa(String nome, String nacionalidade){
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnorePaths("id", "dataNascimento", "dataCadastro") // CAMPOS IGNORADOS
                .withIgnoreNullValues() // IGNORA VALORES NULOS
                .withIgnoreCase() // IGNORA SE SÃO LETRAS MAIÚSCULAS OU MINÚSCULAS
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // PARTE DO TEXT0 () LIKE SQL
        Example<Autor> autorExample = Example.of(autor, matcher); // RECEBENDO OBJETO AUTOR, CONFIGURAÇÃO DE FILTRO
        return repository.findAll(autorExample);
    }

    public void deletar(Autor autor){
        repository.delete(autor);
    }

    public boolean possuiLivro(Autor autor){// Verificar se o autor possui livro vinculado
        return livroRepository.existsByAutor(autor);
    }
}
