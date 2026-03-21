package com.awangelo.msnucleo.service

import com.awangelo.msnucleo.client.MsCompetenciaClient
import com.awangelo.msnucleo.dto.CandidatoResponseDTO
import com.awangelo.msnucleo.dto.CompetenciaDTO
import com.awangelo.msnucleo.dto.CurtidaRequestDTO
import com.awangelo.msnucleo.dto.VagaResponseDTO
import com.awangelo.msnucleo.model.Candidato
import com.awangelo.msnucleo.model.Curtida
import com.awangelo.msnucleo.model.OrigemCurtidaEnum
import com.awangelo.msnucleo.model.Vaga
import com.awangelo.msnucleo.repository.CandidatoRepository
import com.awangelo.msnucleo.repository.CurtidaRepository
import com.awangelo.msnucleo.repository.VagaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CurtidaService {

    private final CurtidaRepository curtidaRepository
    private final CandidatoRepository candidatoRepository
    private final VagaRepository vagaRepository
    private final MsCompetenciaClient msCompetenciaClient

    CurtidaService(CurtidaRepository curtidaRepository,
                   CandidatoRepository candidatoRepository,
                   VagaRepository vagaRepository,
                   MsCompetenciaClient msCompetenciaClient) {
        this.curtidaRepository = curtidaRepository
        this.candidatoRepository = candidatoRepository
        this.vagaRepository = vagaRepository
        this.msCompetenciaClient = msCompetenciaClient
    }

    @Transactional
    boolean processarCurtida(CurtidaRequestDTO dto) {
        Candidato candidato = candidatoRepository.findById(dto.candidatoId)
                .orElseThrow({
                    new RuntimeException("Candidato não encontrado: " + dto.candidatoId)
                })

        Vaga vaga = vagaRepository.findById(dto.vagaId)
                .orElseThrow({
                    new RuntimeException("Vaga não encontrada: " + dto.vagaId)
                })

        if (curtidaRepository.existsByCandidatoIdAndVagaIdAndOrigemCurtida(dto.candidatoId, dto.vagaId, dto.origemCurtida)) {
            throw new RuntimeException("Curtida já registrada")
        }

        Curtida curtida = new Curtida(
                candidato: candidato,
                vaga: vaga,
                origemCurtida: dto.origemCurtida
        )
        curtidaRepository.save(curtida)

        OrigemCurtidaEnum origemMatch = (dto.origemCurtida == OrigemCurtidaEnum.CANDIDATO) ?
                OrigemCurtidaEnum.EMPRESA : OrigemCurtidaEnum.CANDIDATO

        boolean isMatch = curtidaRepository.existsByCandidatoIdAndVagaIdAndOrigemCurtida(dto.candidatoId, dto.vagaId, origemMatch)

        return isMatch
    }

    @Transactional(readOnly = true)
    List<VagaResponseDTO> listarMatchesCandidato(Long candidatoId) {
        List<Vaga> vagas = curtidaRepository.findMatchesForCandidato(candidatoId)

        return vagas.collect { vaga ->
            List<CompetenciaDTO> competencias = []
            try {
                competencias = msCompetenciaClient.getCompetenciasVaga(vaga.id)
            } catch (Exception e) {
                // Ignore error fetching competencies
            }
            return new VagaResponseDTO(vaga, competencias)
        }
    }

    @Transactional(readOnly = true)
    List<CandidatoResponseDTO> listarMatchesVaga(Long vagaId) {
        List<Candidato> candidatos = curtidaRepository.findMatchesForVaga(vagaId)

        return candidatos.collect { candidato ->
            List<CompetenciaDTO> competencias = []
            try {
                competencias = msCompetenciaClient.getCompetenciasCandidato(candidato.id)
            } catch (Exception e) {
                // Ignore error fetching competencies
            }
            return new CandidatoResponseDTO(candidato, competencias)
        }
    }

}
