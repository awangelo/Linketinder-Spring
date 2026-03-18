package com.awangelo.mscompetencia.repository

import com.awangelo.mscompetencia.model.CandidatoCompetencia
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CandidatoCompetenciaRepository extends JpaRepository<CandidatoCompetencia, Long> {

    List<CandidatoCompetencia> findByCandidatoId(Long candidatoId)

}