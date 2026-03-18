package com.awangelo.msnucleo.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

class CandidatoRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    String nome

    @NotBlank(message = "O sobrenome é obrigatório")
    String sobrenome

    LocalDate dataNascimento

    @Email(message = "E-mail com formato inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    String email

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter exatos 11 dígitos")
    String cpf

    @NotBlank(message = "O país é obrigatório")
    String pais

    String estado

    @NotBlank(message = "O CEP é obrigatório")
    String cep

    String descricao

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    String senha

    String telefone
    String linkedin

}
