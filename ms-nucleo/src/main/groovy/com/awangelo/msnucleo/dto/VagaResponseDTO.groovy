package com.awangelo.msnucleo.dto

import com.awangelo.msnucleo.model.Vaga

class VagaResponseDTO {

    Long id
    String nome
    String descricao
    String localVaga
    Long empresaId
    List<CompetenciaDTO> competencias

    VagaResponseDTO(Vaga vaga, List<CompetenciaDTO> competencias) {
        this.id = vaga.id
        this.nome = vaga.nome
        this.descricao = vaga.descricao
        this.localVaga = vaga.localVaga
        this.empresaId = vaga.empresa.id
        this.competencias = competencias
    }

}
