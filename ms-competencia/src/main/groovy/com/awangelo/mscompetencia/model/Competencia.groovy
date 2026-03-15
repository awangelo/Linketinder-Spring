package com.awangelo.mscompetencia.model

import jakarta.persistence.*

@Entity
@Table(name = "competencia")
class Competencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable = false, length = 50, unique = true)
    String nome
}