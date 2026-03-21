package com.awangelo.msnucleo.dto

import com.awangelo.msnucleo.model.OrigemCurtidaEnum
import jakarta.validation.constraints.NotNull

class CurtidaRequestDTO {

    @NotNull(message = "O ID do candidato é obrigatório")
    Long candidatoId

    @NotNull(message = "O ID da vaga é obrigatório")
    Long vagaId

    @NotNull(message = "A origem da curtida é obrigatória")
    OrigemCurtidaEnum origemCurtida

}
