package com.awangelo.mscompetencia.service

import com.awangelo.mscompetencia.dto.CompetenciaRequestDTO
import com.awangelo.mscompetencia.model.CandidatoCompetencia
import com.awangelo.mscompetencia.model.Competencia
import com.awangelo.mscompetencia.repository.CandidatoCompetenciaRepository
import com.awangelo.mscompetencia.repository.CompetenciaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CompetenciaService {

    private final CompetenciaRepository competenciaRepository
    private final CandidatoCompetenciaRepository candidatoCompetenciaRepository

    CompetenciaService(CompetenciaRepository competenciaRepository,
                       CandidatoCompetenciaRepository candidatoCompetenciaRepository) {
        this.competenciaRepository = competenciaRepository
        this.candidatoCompetenciaRepository = candidatoCompetenciaRepository
    }

    @Transactional
    void salvarCompetenciasDoCandidato(Long candidatoId, List<CompetenciaRequestDTO> competenciasSugeridas) {

        for (CompetenciaRequestDTO dto : competenciasSugeridas) {
            String nomeLimpo = dto.nome.trim()

            Competencia competencia = competenciaRepository.findByNomeIgnoreCase(nomeLimpo)
                    .orElseGet({
                        Competencia nova = new Competencia(nome: nomeLimpo)
                        return competenciaRepository.save(nova)
                    })

            CandidatoCompetencia vinculo = new CandidatoCompetencia(
                    candidatoId: candidatoId,
                    competencia: competencia,
                    nivel: dto.nivel
            )

            candidatoCompetenciaRepository.save(vinculo)
        }
    }

}