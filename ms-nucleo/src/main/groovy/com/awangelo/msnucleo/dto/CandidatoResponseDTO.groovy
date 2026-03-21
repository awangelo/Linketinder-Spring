package com.awangelo.msnucleo.dto

import com.awangelo.msnucleo.model.Candidato
import java.time.LocalDate

class CandidatoResponseDTO {

    Long id
    String nome
    String sobrenome
    LocalDate dataNascimento
    String email
    String pais
    String descricao
    List<CompetenciaDTO> competencias

    CandidatoResponseDTO(Candidato candidato) {
        this.id = candidato.id
        this.nome = candidato.nome
        this.sobrenome = candidato.sobrenome
        this.dataNascimento = candidato.dataNascimento
        this.email = candidato.email
        this.pais = candidato.pais
        this.descricao = candidato.descricao
        this.competencias = new ArrayList<>()
    }

    CandidatoResponseDTO(Candidato candidato, List<CompetenciaDTO> competencias) {
        this(candidato)
        this.competencias = competencias
    }

}
