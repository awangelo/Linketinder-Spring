package com.awangelo.mscompetencia.repository

import com.awangelo.mscompetencia.model.VagaCompetencia
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VagaCompetenciaRepository extends JpaRepository<VagaCompetencia, Long> {

    List<VagaCompetencia> findByVagaId(Long vagaId)

}
