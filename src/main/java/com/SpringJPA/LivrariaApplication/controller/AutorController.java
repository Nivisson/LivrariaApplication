package com.SpringJPA.LivrariaApplication.controller;

import com.SpringJPA.LivrariaApplication.controller.mappers.AutorMapper;
import com.SpringJPA.LivrariaApplication.dto.AutorDTO;
import com.SpringJPA.LivrariaApplication.model.Autor;
import com.SpringJPA.LivrariaApplication.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor // Gerando construtor "private final"
public class AutorController implements GenericController {

    private final AutorService service;
    private final AutorMapper mapper;

    @PreAuthorize("hasRole('GERENTE')") // Perfil que pode acessar o cadastro
    @PostMapping // Salvar cadastro do Autor
    public ResponseEntity salvar(@RequestBody @Valid AutorDTO dto) {// Recebe e valida os dados da requisição

        Autor autor = mapper.toEntity(dto); // Autor recebe os dados mapeados do DTO
        service.salvar(autor);

        // CÓDIGO REDIRECIONAMENTO DA URL APÓS SALVAR AUTOR
        // EX: http://localhost:8080/autores/id_do_autor
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(location).body("Autor salvo com sucesso! " + autor);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")  // Permite mais de um perfil de acesso
    // Obter detalhes de um autor pelo ID
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);

        return service // Obtem o autor, transforma em DTO e dá o retorno caso o Autor exista
                .obterPorId(idAutor)
                .map(autor -> {
                    AutorDTO dto = mapper.toDTO(autor);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('GERENTE')") // Perfil que pode acessar o cadastro
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        if (autorOptional.isEmpty()) { // verifica se o Autor esta vazio
            return ResponseEntity.notFound().build();
        }

        service.deletar(autorOptional.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')") // Permite mais de um perfil de acesso
    // PESQUISA DE AUTORES
    public ResponseEntity<List<AutorDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {

        List<Autor> resultado = service.pesquisa(nome, nacionalidade);
        List<AutorDTO> lista = resultado // MAPEANDO CADA RESULTADO PARA AUTOR DTO
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('GERENTE')") // Perfil que pode acessar o cadastro
    public ResponseEntity<Void> atualizar(
            @PathVariable("id") String id, @RequestBody @Valid AutorDTO dto) {

        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        if (autorOptional.isEmpty()) { // VERIFICA SE O AUTOR ESTÁ VAZIO
            return ResponseEntity.notFound().build();
        }

        var autor = autorOptional.get();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());

        service.atualizar(autor);

        return ResponseEntity.noContent().build();
    }
}