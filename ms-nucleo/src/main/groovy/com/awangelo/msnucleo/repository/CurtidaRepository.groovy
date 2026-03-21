package com.awangelo.msnucleo.repository

import com.awangelo.msnucleo.model.Curtida
import com.awangelo.msnucleo.model.OrigemCurtidaEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CurtidaRepository extends JpaRepository<Curtida, Long> {

    Optional<Curtida> findByCandidatoIdAndVagaIdAndOrigemCurtida(Long candidatoId, Long vagaId, OrigemCurtidaEnum origemCurtida)

    // Outro lado tambem curtiu
    boolean existsByCandidatoIdAndVagaIdAndOrigemCurtida(Long candidatoId, Long vagaId, OrigemCurtidaEnum origemCurtida)

}

