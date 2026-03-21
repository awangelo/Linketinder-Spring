package com.awangelo.msnucleo.service

import com.awangelo.msnucleo.client.MsCompetenciaClient
import com.awangelo.msnucleo.dto.CompetenciaDTO
import com.awangelo.msnucleo.dto.VagaCompetenciaDTO
import com.awangelo.msnucleo.dto.VagaRequestDTO
import com.awangelo.msnucleo.dto.VagaResponseDTO
import com.awangelo.msnucleo.model.Empresa
import com.awangelo.msnucleo.model.Vaga
import com.awangelo.msnucleo.repository.EmpresaRepository
import com.awangelo.msnucleo.repository.VagaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VagaService {

    private final VagaRepository vagaRepository
    private final EmpresaRepository empresaRepository
    private final MsCompetenciaClient msCompetenciaClient

    VagaService(VagaRepository vagaRepository, EmpresaRepository empresaRepository, MsCompetenciaClient msCompetenciaClient) {
        this.vagaRepository = vagaRepository
        this.empresaRepository = empresaRepository
        this.msCompetenciaClient = msCompetenciaClient
    }

    @Transactional
    void adicionarCompetencias(Long vagaId, List<CompetenciaDTO> competencias) {
        VagaCompetenciaDTO competenciaDTO = new VagaCompetenciaDTO(
                vagaId: vagaId,
                competencias: competencias
        )
        msCompetenciaClient.enviarCompetenciasVaga(competenciaDTO)
    }

    @Transactional
    Vaga criarVaga(VagaRequestDTO dto) {
        Empresa empresa = empresaRepository.findById(dto.empresaId)
                .orElseThrow({
                    new RuntimeException("Empresa não encontrada: " + dto.empresaId)
                })

        Vaga vaga = new Vaga(
                nome: dto.nome,
                descricao: dto.descricao,
                localVaga: dto.localVaga,
                empresa: empresa
        )
        Vaga vagaSalva = vagaRepository.save(vaga)

        adicionarCompetencias(vagaSalva.id, dto.competencias)

        return vagaSalva
    }

    @Transactional(readOnly = true)
    List<VagaResponseDTO> listarVagas() {
        List<Vaga> vagas = vagaRepository.findAll()

        return vagas.collect { vaga ->
            List<CompetenciaDTO> competencias = [] as List<CompetenciaDTO>
            try {
                competencias = msCompetenciaClient.getCompetenciasVaga(vaga.id)
            } catch (Exception e) {
                // ms-competencia esta off, o que nao iria acontecer com microservicos ou uma fila.
                println "Erro ao buscar competências para vaga ${vaga.id}: ${e.message}"
            }

            return new VagaResponseDTO(vaga, competencias)
        }
    }

}
