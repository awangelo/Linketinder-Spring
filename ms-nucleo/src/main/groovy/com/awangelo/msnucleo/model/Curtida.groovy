package com.awangelo.msnucleo.model

import jakarta.persistence.*

@Entity
@Table(name = "curtida", uniqueConstraints = [
        @UniqueConstraint(columnNames = ["candidato_id", "vaga_id", "origem_curtida"])
])
class Curtida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidato_id", nullable = false)
    Candidato candidato

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaga_id", nullable = false)
    Vaga vaga

    @Enumerated(EnumType.STRING)
    @Column(name = "origem_curtida", nullable = false, length = 10)
    OrigemCurtidaEnum origemCurtida
}