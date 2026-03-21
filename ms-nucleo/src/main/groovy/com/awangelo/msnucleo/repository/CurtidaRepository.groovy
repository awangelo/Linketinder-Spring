package com.awangelo.msnucleo.repository

import com.awangelo.msnucleo.model.Curtida
import com.awangelo.msnucleo.model.OrigemCurtidaEnum
import com.awangelo.msnucleo.model.Candidato
import com.awangelo.msnucleo.model.Vaga
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CurtidaRepository extends JpaRepository<Curtida, Long> {

    Optional<Curtida> findByCandidatoIdAndVagaIdAndOrigemCurtida(Long candidatoId, Long vagaId, OrigemCurtidaEnum origemCurtida)

    // Outro lado tambem curtiu
    boolean existsByCandidatoIdAndVagaIdAndOrigemCurtida(Long candidatoId, Long vagaId, OrigemCurtidaEnum origemCurtida)

    @Query("""
        SELECT c.vaga FROM Curtida c 
        WHERE c.candidato.id = :candidatoId 
        AND c.origemCurtida = 'CANDIDATO'
        AND EXISTS (
            SELECT 1 FROM Curtida c2 
            WHERE c2.vaga.id = c.vaga.id 
            AND c2.candidato.id = c.candidato.id 
            AND c2.origemCurtida = 'EMPRESA'
        )
    """)
    List<Vaga> findMatchesForCandidato(@Param("candidatoId") Long candidatoId)

    @Query("""
        SELECT c.candidato FROM Curtida c 
        WHERE c.vaga.id = :vagaId 
        AND c.origemCurtida = 'EMPRESA'
        AND EXISTS (
            SELECT 1 FROM Curtida c2 
            WHERE c2.vaga.id = c.vaga.id 
            AND c2.candidato.id = c.candidato.id 
            AND c2.origemCurtida = 'CANDIDATO'
        )
    """)
    List<Candidato> findMatchesForVaga(@Param("vagaId") Long vagaId)

    @Query("""
        SELECT c.candidato FROM Curtida c 
        WHERE c.vaga.id = :vagaId 
        AND c.origemCurtida = 'CANDIDATO'
        AND NOT EXISTS (
            SELECT 1 FROM Curtida c2 
            WHERE c2.vaga.id = c.vaga.id 
            AND c2.candidato.id = c.candidato.id 
            AND c2.origemCurtida = 'EMPRESA'
        )
    """)
    List<Candidato> findCandidatosInteressadosVaga(@Param("vagaId") Long vagaId)

}
