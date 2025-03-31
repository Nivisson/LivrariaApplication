package com.SpringJPA.LivrariaApplication.service;

import com.SpringJPA.LivrariaApplication.model.Usuario;
import com.SpringJPA.LivrariaApplication.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Gerando construtor private final
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public void salvar(Usuario usuario){ // Salvar usuário na base
        var senha = usuario.getSenha(); // // Obtém a senha enviada na requisição e armazena na variável 'senha'
        usuario.setSenha(encoder.encode(senha)); // Codifica a senha antes de armazená-la
        repository.save(usuario);
    }

    public Usuario obterPorLogin(String login){ // Obtem Usuario por login
        return repository.findByLogin(login);
    }

    public Usuario obterPorEmail(String email){ // Obtem Usuario por emeail
        return repository.findByEmail(email);
    }
}