package com.awangelo.msnucleo.model

import jakarta.persistence.*

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

    @Column(columnDefinition = "TEXT")
    String descricao

    @Column(nullable = false)
    String senha

}