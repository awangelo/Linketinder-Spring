package com.awangelo.mscompetencia.repository

import com.awangelo.mscompetencia.model.Competencia
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompetenciaRepository extends JpaRepository<Competencia, Long> {

    Optional<Competencia> findByNomeIgnoreCase(String nome)

}