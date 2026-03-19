package com.awangelo.mscompetencia.service

import com.awangelo.mscompetencia.dto.CompetenciaRequestDTO
import com.awangelo.mscompetencia.model.CandidatoCompetencia
import com.awangelo.mscompetencia.model.Competencia
import com.awangelo.mscompetencia.model.VagaCompetencia
import com.awangelo.mscompetencia.repository.CandidatoCompetenciaRepository
import com.awangelo.mscompetencia.repository.CompetenciaRepository
import com.awangelo.mscompetencia.repository.VagaCompetenciaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CompetenciaService {

    private final CompetenciaRepository competenciaRepository
    private final CandidatoCompetenciaRepository candidatoCompetenciaRepository
    private final VagaCompetenciaRepository vagaCompetenciaRepository

    CompetenciaService(CompetenciaRepository competenciaRepository,
                       CandidatoCompetenciaRepository candidatoCompetenciaRepository,
                       VagaCompetenciaRepository vagaCompetenciaRepository) {
        this.competenciaRepository = competenciaRepository
        this.candidatoCompetenciaRepository = candidatoCompetenciaRepository
        this.vagaCompetenciaRepository = vagaCompetenciaRepository
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

    @Transactional
    void salvarCompetenciasDaVaga(Long vagaId, List<String> competenciasSugeridas) {

        for (String nome : competenciasSugeridas) {
            String nomeLimpo = nome.trim()

            Competencia competencia = competenciaRepository.findByNomeIgnoreCase(nomeLimpo)
                    .orElseGet({
                        Competencia nova = new Competencia(nome: nomeLimpo)
                        return competenciaRepository.save(nova)
                    })

            VagaCompetencia vinculo = new VagaCompetencia(
                    vagaId: vagaId,
                    competencia: competencia
            )

            vagaCompetenciaRepository.save(vinculo)
        }
    }

}