package com.SpringJPA.LivrariaApplication.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Getter // Anotação Lombok(Criação automática dos Getters)
@Setter // Anotação Lombok(Criação automática dos Setters)
@ToString(exclude = {"livros"})
@Entity
@Table(name= "autor", schema = "public") // Mapeando tabela, schema padrão
@EntityListeners(AuditingEntityListener.class) // anotação para monitoramento das datas
public class Autor {

    @Id // identificando que é a chave primária
    @Column(name = "id") // Mapeando com a coluna do banco (OPCIONAL)
    @GeneratedValue(strategy = GenerationType.UUID) // Gerando automáticamente ID
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)// Mapeando com a coluna do banco
    private String nome;

    @Column(name = "data_nascimento", nullable = false)// Mapeando com a coluna do banco
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)// Mapeando com a coluna do banco
    private String nacionalidade;

    @CreatedDate // ANOTAÇÃO INSERIR DATA DE CADASTRO
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro; // CAMPO LÓGICO NO SISTEMA

    @LastModifiedDate // ANOTAÇÃO ATUALIZAR DATA DE CADASTRO
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao; // CAMPO LÓGICO NO SISTEMA

    @ManyToOne // Muitos registros de autor para 1 usuário
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "autor", // Mapeamento de um para muitos(Será chave estrangeira em Livro)
            fetch = FetchType.LAZY) // Tipo de Carregamento

    private List<Livro> livros; // Obter livros de um autor

}