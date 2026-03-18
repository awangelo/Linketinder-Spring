package com.awangelo.msnucleo.model

import jakarta.persistence.*
import org.hibernate.Length

import java.time.LocalDate

@Entity
@Table(name = "candidato")
class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable = false, length = 100)
    String nome

    @Column(nullable = false, length = 100)
    String sobrenome

    @Column(name = "data_nascimento", nullable = false)
    LocalDate dataNascimento

    @Column(nullable = false, length = 150, unique = true)
    String email

    @Column(nullable = false, length = 11, unique = true)
    String cpf

    @Column(nullable = false, length = 50)
    String pais

    @Column(length = 2)
    String estado

    @Column(nullable = false, length = 9)
    String cep

    @Column(columnDefinition = "TEXT")
    String descricao

    @Column(nullable = false)
    String senha

    @Column(length = 11)
    String telefone

    String linkedin
}