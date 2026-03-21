package com.awangelo.msnucleo.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class EmpresaRequestDTO {

    @NotBlank(message = "O nome da empresa é obrigatório")
    String nome

    @NotBlank(message = "O CNPJ é obrigatório")
    @Size(min = 14, max = 18, message = "CNPJ inválido")
    String cnpj

    @Email(message = "E-mail com formato inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    String email

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    String senha

    String descricao
    String pais
    String estado

}
