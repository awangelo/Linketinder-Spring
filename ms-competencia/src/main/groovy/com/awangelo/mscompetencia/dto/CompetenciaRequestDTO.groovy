package com.awangelo.mscompetencia.dto

import com.awangelo.mscompetencia.model.NivelCompetenciaEnum
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class CompetenciaRequestDTO {

    @NotBlank(message = "O nome da competência não pode estar vazio")
    String nome

    @NotNull(message = "O nível da competência é obrigatório")
    NivelCompetenciaEnum nivel

}
