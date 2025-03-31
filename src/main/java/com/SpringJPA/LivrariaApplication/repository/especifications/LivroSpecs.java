package com.SpringJPA.LivrariaApplication.repository.especifications;

import com.SpringJPA.LivrariaApplication.model.GeneroLivro;
import com.SpringJPA.LivrariaApplication.model.Livro;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecs {

    // PESQUISA LIVRO PELO ISBN
    public static Specification<Livro> isbnEqual(String isbn){
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    // PESQUISA LIVRO POR PARTE DO NOME DO TÍTULO DO LIVRO
    public static Specification<Livro> tituloLike(String titulo){
        // upper(livro.titulo) like (%:param%)
        return (root, query, cb) ->
                cb.like( cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    // PESQUISA LIVRO PELO GENERO
    public static Specification<Livro> generoEqual(GeneroLivro genero){
        return (root, query, cb) -> cb.equal(root.get("genero"), genero);
    }

    // PESQUISA LIVRO PELO NOME DE PUBLICAÇÃO
    public static Specification<Livro> anoPublicacaoEqual(Integer anoPublicacao){
        // and to_char(data_publicacao, 'YYYY') = :anoPublicacao
        return (root, query, cb) ->
                cb.equal( cb.function("to_char", String.class,
                        root.get("dataPublicacao"), cb.literal("YYYY")),anoPublicacao.toString());
    }

    // PESQUISA LIVRO POR PARTE DO NOME DO AUTOR
    public static Specification<Livro> nomeAutorLike(String nome){
        return (root, query, cb) -> {
            Join<Object, Object> joinAutor = root.join("autor", JoinType.INNER);
            return cb.like( cb.upper(joinAutor.get("nome")), "%" + nome.toUpperCase() + "%" );
        };
    }
}
