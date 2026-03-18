package com.awangelo.mscompetencia.controller

import com.awangelo.mscompetencia.dto.CompetenciaRequestDTO
import com.awangelo.mscompetencia.service.CompetenciaService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/competencia")
@CrossOrigin(origins = "*")
class CompetenciaController {

    private final CompetenciaService competenciaService

    CompetenciaController(CompetenciaService competenciaService) {
        this.competenciaService = competenciaService
    }

    @PostMapping("/candidato/{candidatoId}")
    ResponseEntity<Void> adicionarCompetenciasCandidato(
            @PathVariable Long candidatoId,
            @RequestBody @Valid List<CompetenciaRequestDTO> competencias) {

        competenciaService.salvarCompetenciasDoCandidato(candidatoId, competencias)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}
