package com.sistemadegestaodeprojetos.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity(name = "PROJETO")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "TITULO", length = 100, nullable = false)
    private String titulo;

    @Column(name = "DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    public void prePersist() {
        if (dataCriacao == null) {
            dataCriacao = LocalDateTime.now();
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status;

}

// O uso de columnDefinition = "varchar(100)" não é necessário porque:
// 1. O JPA já define automaticamente String como VARCHAR(255).
// 2. Se quisermos limitar o tamanho, usamos length = 100, que é mais portável.
// 3. columnDefinition pode causar problemas ao mudar de banco de dados (MySQL,
// PostgreSQL, etc.)
// 4. Só devemos usá-lo quando realmente precisarmos de um tipo específico, como
// "TEXT" ou "BOOLEAN DEFAULT false".
