package com.SpringJPA.LivrariaApplication.repository;

import com.SpringJPA.LivrariaApplication.model.Autor;
import com.SpringJPA.LivrariaApplication.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> , JpaSpecificationExecutor<Livro> {

    Optional<Livro> findByIsbn(String isbn); // BUSCA POR ISBN

    boolean existsByAutor(Autor autor); // Verificar se já existe autor no repositório de livros

}
