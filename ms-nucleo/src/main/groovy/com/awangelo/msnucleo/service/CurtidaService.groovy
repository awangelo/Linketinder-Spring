package com.awangelo.msnucleo.service

import com.awangelo.msnucleo.dto.CurtidaRequestDTO
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

    CurtidaService(CurtidaRepository curtidaRepository,
                   CandidatoRepository candidatoRepository,
                   VagaRepository vagaRepository) {
        this.curtidaRepository = curtidaRepository
        this.candidatoRepository = candidatoRepository
        this.vagaRepository = vagaRepository
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

}

