package com.awangelo.msnucleo.service

import com.awangelo.msnucleo.client.MsCompetenciaClient
import com.awangelo.msnucleo.dto.CandidatoRequestDTO
import com.awangelo.msnucleo.dto.CandidatoResponseDTO
import com.awangelo.msnucleo.dto.CompetenciaDTO
import com.awangelo.msnucleo.model.Candidato
import com.awangelo.msnucleo.repository.CandidatoRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CandidatoService {

    private final CandidatoRepository candidatoRepository
    private final PasswordEncoder passwordEncoder
    private final MsCompetenciaClient msCompetenciaClient

    CandidatoService(CandidatoRepository candidatoRepository, PasswordEncoder passwordEncoder,
                     MsCompetenciaClient msCompetenciaClient) {
        this.candidatoRepository = candidatoRepository
        this.passwordEncoder = passwordEncoder
        this.msCompetenciaClient = msCompetenciaClient
    }

    @Transactional
    CandidatoResponseDTO salvar(CandidatoRequestDTO dto) {
        String senha = passwordEncoder.encode(dto.senha)

        Candidato candidato = new Candidato(
                nome: dto.nome,
                sobrenome: dto.sobrenome,
                dataNascimento: dto.dataNascimento,
                email: dto.email,
                cpf: dto.cpf,
                pais: dto.pais,
                descricao: dto.descricao,
                senha: senha
        )

        Candidato candidatoSalvo = candidatoRepository.save(candidato)

        return new CandidatoResponseDTO(candidatoSalvo)
    }

    @Transactional(readOnly = true)
    List<CandidatoResponseDTO> listarCandidatos() {
        List<Candidato> candidatos = candidatoRepository.findAll()

        return candidatos.collect { candidato ->
            List<CompetenciaDTO> competencias = new ArrayList<>()
            try {
                competencias = msCompetenciaClient.getCompetenciasCandidato(candidato.id)
            } catch (Exception e) {
                println "Erro ao buscar competências para candidato ${candidato.id}: ${e.message}"
            }

            return new CandidatoResponseDTO(candidato, competencias)
        }
    }

}