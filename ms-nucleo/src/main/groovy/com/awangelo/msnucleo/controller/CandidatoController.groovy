package com.awangelo.msnucleo.controller

import com.awangelo.msnucleo.dto.CandidatoRequestDTO
import com.awangelo.msnucleo.dto.CandidatoResponseDTO
import com.awangelo.msnucleo.service.CandidatoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/candidatos")
@CrossOrigin(origins = "*")
class CandidatoController {

    private final CandidatoService candidatoService

    CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService
    }

    @PostMapping
    ResponseEntity<CandidatoResponseDTO> cadastrarCandidato(@Valid @RequestBody CandidatoRequestDTO requestDTO) {
        // @Valid roda o Bean Validation ANTES de entrar no metodo.
        CandidatoResponseDTO responseDTO = candidatoService.salvar(requestDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO)
    }

}
