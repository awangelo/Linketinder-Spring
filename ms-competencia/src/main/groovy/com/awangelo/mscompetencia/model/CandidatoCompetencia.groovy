package com.awangelo.mscompetencia.model

import jakarta.persistence.*

@Entity
@Table(name = "candidato_competencia")
class CandidatoCompetencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(name = "candidato_id", nullable = false)
    Long candidatoId

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competencia_id", nullable = false)
    Competencia competencia

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    NivelCompetenciaEnum nivel
}