package com.SpringJPA.LivrariaApplication.service;

import com.SpringJPA.LivrariaApplication.model.GeneroLivro;
import com.SpringJPA.LivrariaApplication.model.Livro;
import com.SpringJPA.LivrariaApplication.model.Usuario;
import com.SpringJPA.LivrariaApplication.repository.LivroRepository;
import com.SpringJPA.LivrariaApplication.service.customSecurity.SecurityService;
import com.SpringJPA.LivrariaApplication.service.validation.LivroValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

import static com.SpringJPA.LivrariaApplication.repository.especifications.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final LivroValidation validador;
    private final SecurityService securityService;

    public Livro salvar(Livro livro) {
        validador.validar(livro);
        Usuario usuario = securityService.obterUsuarioLogado();
        livro.setUsuario(usuario);
        return repository.save(livro);
    }

    public void atualizar(Livro livro) {
        if(livro.getId() == null){
            throw new IllegalArgumentException("Para atualizar, o livro precisa estar cadastrado");
        }
        validador.validar(livro);
        repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Livro livro){
        repository.delete(livro);
    }

    //isbn, titulo, nome autor, genero, ano de publicação
    public Page<Livro> pesquisa( // Pesquisa paginada de livros utilizando Page e Specification
            String isbn,
            String titulo,
            String nomeAutor,
            GeneroLivro genero,
            Integer anoPublicacao,
            Integer pagina,
            Integer tamanhoPagina){

        // CRIANDO SPECIFICATION PARA CONSULTA
        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction() );

        // Equal(Termos identicos(Banco e pesquisa), LIKE(Termos parcialmente iguais))
        if(isbn != null){ // VERIFICA SE O CAMPO ESTÁ PREENCHIDO E CHAMA A FUNÇÃO DE PESQUISA
            specs = specs.and(isbnEqual(isbn));
        }
        if(titulo != null){ // CON
            specs = specs.and(tituloLike(titulo));
        }
        if(genero != null){
            specs = specs.and(generoEqual(genero));
        }
        if(anoPublicacao != null){
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }
        if(nomeAutor != null){
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        // Recebe parametro número da página e tamanho, fornecido pelo front end
        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return repository.findAll(specs, pageRequest);
    }
}
