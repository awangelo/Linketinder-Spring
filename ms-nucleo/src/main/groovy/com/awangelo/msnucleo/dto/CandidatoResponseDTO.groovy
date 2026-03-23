package com.awangelo.msnucleo.dto

import com.awangelo.msnucleo.model.Candidato

class CandidatoResponseDTO {

    Long id
    String descricao
    List<CompetenciaDTO> competencias

    CandidatoResponseDTO(Candidato candidato) {
        this.id = candidato.id
        this.descricao = candidato.descricao
        this.competencias = new ArrayList<>()
    }

    CandidatoResponseDTO(Candidato candidato, List<CompetenciaDTO> competencias) {
        this(candidato)
        this.competencias = competencias
    }

}
