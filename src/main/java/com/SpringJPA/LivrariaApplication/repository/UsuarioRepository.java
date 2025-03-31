package com.SpringJPA.LivrariaApplication.repository;

import com.SpringJPA.LivrariaApplication.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Usuario findByLogin(String login); // PESQUISA POR LOGIN
    Usuario findByEmail(String email); // PESQUISA POR EMAIL
}
