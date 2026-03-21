package com.awangelo.msnucleo.model

import jakarta.persistence.*

@Entity
@Table(name = "empresa")
class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable = false, length = 100)
    String nome

    @Column(nullable = false, length = 14, unique = true)
    String cnpj

    @Column(nullable = false, length = 150, unique = true)
    String email

    @Column(columnDefinition = "TEXT")
    String descricao

    @Column(nullable = false, length = 50)
    String pais

    @Column(length = 2)
    String estado

    @Column(nullable = false)
    String senha
}