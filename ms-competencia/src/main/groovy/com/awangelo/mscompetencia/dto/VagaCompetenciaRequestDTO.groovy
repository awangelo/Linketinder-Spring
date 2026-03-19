package com.awangelo.mscompetencia.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class VagaCompetenciaRequestDTO {

    @NotNull(message = "O ID da vaga é obrigatório")
    Long vagaId

    @NotEmpty(message = "A lista de competências não pode ser vazia")
    List<String> competencias

}

