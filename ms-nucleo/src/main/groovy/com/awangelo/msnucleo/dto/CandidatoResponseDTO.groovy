package com.awangelo.msnucleo.dto

import com.awangelo.msnucleo.model.Candidato

class CandidatoResponseDTO {

    Long id

    CandidatoResponseDTO(Candidato candidato) {
        this.id = candidato.id
    }

}
