package com.SpringJPA.LivrariaApplication.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name= "livro", schema = "public") // Mapeando tabela, schema padrão
@Data // Gerando Setter / Setter / to string (LOMBOK)
@ToString(exclude = "autor")
public class Livro {

    @Id // identificando que é a chave primária
    @Column(name = "id") // Mapeando com a coluna do banco (OPCIONAL)
    @GeneratedValue(strategy = GenerationType.UUID) // Gerando automáticamente ID
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)// Mapeando com a coluna do banco
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)// Mapeando com a coluna do banco
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)// Mapeando com a coluna do banco
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING) // Tipo Enum / Tipo String
    @Column(name = "genero", length = 30, nullable = false)// Mapeando com a coluna do banco
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2)// Mapeando com a coluna do banco
    // (PRECISION = POSIÇÕES/ SCALE = CASAS DECIMAIS )
    private BigDecimal preco; // Double não suporta o mapeamento acima

    @ManyToOne(fetch = FetchType.LAZY) // Mapeamento de muitos para um e tipo carregamento pesquisa
    @JoinColumn(name = "id_autor")// definindo a chave estrangeira
    private Autor autor;

    @CreatedDate // ANOTAÇÃO INSERIR DATA DE CADASTRO
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate // ANOTAÇÃO ATUALIZAR DATA DE CADASTRO
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @ManyToOne // Muitos registros de autor para 1 usuário
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}