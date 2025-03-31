package com.SpringJPA.LivrariaApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login") // MAPEANDO PAGINA DE LOGIN
    public String paginaLogin(){
        return "login";
    }
}