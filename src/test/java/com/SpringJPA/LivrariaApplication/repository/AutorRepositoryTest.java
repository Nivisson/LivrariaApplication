package com.SpringJPA.LivrariaApplication.repository;

import com.SpringJPA.LivrariaApplication.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired // injetando interface AutorRepository
    AutorRepository repository;

    @Test // Teste salvando autor
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1980, 2, 20));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo" + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("COLOCAR ID DO AUTOR P/ ATUALIZAR");

        Optional<Autor> possivelAutor = repository.findById(id);
        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor");
            System.out.println(autorEncontrado);
            autorEncontrado.setDataNascimento(LocalDate.of(1960,1,30));
            repository.save(autorEncontrado);

        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);

    }

    @Test
    public void countTest(){
        System.out.println("Contagem dos autores" + repository.count());
    }


    @Test
    public void deleteTest(){ // deleta diretamente pelo id
        var id = UUID.fromString("COLOCAR ID DO AUTOR PARA DELETAR");
        repository.deleteById(id);
    }
    @Test
    public void deleteTest2(){ // busca o autor e deleta pelo id
        var id = UUID.fromString("");
        var maria = repository.findById(id).get();
        repository.deleteById(id);
    }
}
