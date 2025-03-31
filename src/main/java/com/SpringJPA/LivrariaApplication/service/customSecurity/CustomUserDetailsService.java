package com.SpringJPA.LivrariaApplication.service.customSecurity;

import com.SpringJPA.LivrariaApplication.model.Usuario;
import com.SpringJPA.LivrariaApplication.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor // GERANDO CONSTRUTOT 'PRIVATE FINAL'
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService service;

    @Override // SOBRESCREVENDO O 'UserDetails' DO SPRING
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = service.obterPorLogin(login);

        if(usuario == null){ // VERIFICA DE O USUÁRIO EXISTE
            throw new UsernameNotFoundException("Usuario não encontrado!");
        }

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(usuario.getRoles().toArray(new String[usuario.getRoles().size()]))
                .build();
    }
}