package com.awangelo.msnucleo.service

import com.awangelo.msnucleo.dto.CandidatoRequestDTO
import com.awangelo.msnucleo.dto.CandidatoResponseDTO
import com.awangelo.msnucleo.model.Candidato
import com.awangelo.msnucleo.repository.CandidatoRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CandidatoService {

    private final CandidatoRepository candidatoRepository
    private final PasswordEncoder passwordEncoder

    CandidatoService(CandidatoRepository candidatoRepository, PasswordEncoder passwordEncoder) {
        this.candidatoRepository = candidatoRepository
        this.passwordEncoder = passwordEncoder
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

}