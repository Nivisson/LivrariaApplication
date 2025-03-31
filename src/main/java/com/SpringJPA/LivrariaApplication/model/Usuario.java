package com.SpringJPA.LivrariaApplication.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;
import org.hibernate.annotations.Type;
import java.util.List;

// Permite enviar Array em uma única coluna para Postgres, Adicionar dependência Hypersistence
import io.hypersistence.utils.hibernate.type.array.ListArrayType;


@Entity
@Table
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column //Mapeando com a coluna do banco
    private String login;

    @Column //Mapeando com a coluna do banco
    private String senha;

    @Column //Mapeando com a coluna do banco
    private String email;

    @Type(ListArrayType.class) // Mapear array
    @Column(name = "roles", columnDefinition = "varchar[]")// Mapeando coluna e definindo Array de String
    private List<String> roles;
}
