package com.awangelo.msnucleo.model

import jakarta.persistence.*

@Entity
@Table(name = "vaga")
class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    Empresa empresa

    @Column(nullable = false, length = 100)
    String nome

    @Column(columnDefinition = "TEXT")
    String descricao

    @Column(name = "local_vaga", nullable = false, length = 100)
    String localVaga
}