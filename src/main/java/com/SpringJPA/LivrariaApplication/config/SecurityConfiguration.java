package com.SpringJPA.LivrariaApplication.config;

import com.SpringJPA.LivrariaApplication.service.UsuarioService;
import com.SpringJPA.LivrariaApplication.service.customSecurity.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true) // HABILITA CONTROLE DE ACESSO NOS CONTROLLERS
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // desativa a proteção CSRF (INTEGRACAO FRONT END)
                .httpBasic(Customizer.withDefaults())
                .formLogin(configurer -> {
                    configurer.loginPage("/login"); // PAGINA DE LOGIN DA APLICAÇÃO
                })
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login/**").permitAll(); // ACESSO TELA LOGIN LIBERADO
                    authorize.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll(); // CADASTRO DE USUARIOS LIBERADO

                    authorize.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ // UTILIZANDO BCRYPT COMO CODIFICADOR DE SENHAS
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioService usuarioService){

        return new CustomUserDetailsService(usuarioService);
    }
}