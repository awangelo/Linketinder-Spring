package com.awangelo.mscompetencia.model

import jakarta.persistence.*

@Entity
@Table(name = "vaga_competencia")
class VagaCompetencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(name = "vaga_id", nullable = false)
    Long vagaId

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competencia_id", nullable = false)
    Competencia competencia
}