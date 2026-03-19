package com.awangelo.msnucleo.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class VagaRequestDTO {

    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId

    @NotEmpty(message = "O nome da vaga é obrigatório")
    String nome

    String descricao

    @NotEmpty(message = "O local da vaga é obrigatório")
    String localVaga

    @NotEmpty(message = "A lista de competências não pode ser vazia")
    List<String> competencias

}
