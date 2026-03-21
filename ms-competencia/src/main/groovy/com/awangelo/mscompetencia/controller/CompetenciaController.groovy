package com.awangelo.mscompetencia.controller

import com.awangelo.mscompetencia.dto.CompetenciaRequestDTO
import com.awangelo.mscompetencia.dto.VagaCompetenciaRequestDTO
import com.awangelo.mscompetencia.service.CompetenciaService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/competencias")
@CrossOrigin(origins = "*")
class CompetenciaController {

    private final CompetenciaService competenciaService

    CompetenciaController(CompetenciaService competenciaService) {
        this.competenciaService = competenciaService
    }

    @PostMapping("/candidatos/{candidatoId}")
    ResponseEntity<Void> adicionarCompetenciasCandidato(
            @PathVariable("candidatoId") Long candidatoId,
            @RequestBody @Valid List<CompetenciaRequestDTO> competencias) {

        competenciaService.salvarCompetenciasDoCandidato(candidatoId, competencias)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/vagas")
    ResponseEntity<Void> adicionarCompetenciasVaga(
            @RequestBody @Valid VagaCompetenciaRequestDTO dto) {

        competenciaService.salvarCompetenciasDaVaga(dto.vagaId, dto.competencias)

        return ResponseEntity.ok().build()
    }

    @GetMapping("/candidatos/{candidatoId}")
    ResponseEntity<List<CompetenciaRequestDTO>> listarCompetenciasCandidato(
            @PathVariable("candidatoId") Long candidatoId) {

        List<CompetenciaRequestDTO> lista = competenciaService.listarCompetenciasDoCandidato(candidatoId)

        return ResponseEntity.ok(lista)
    }

    @GetMapping("/vagas/{vagaId}")
    ResponseEntity<List<CompetenciaRequestDTO>> listarCompetenciasVaga(
            @PathVariable("vagaId") Long vagaId) {

        List<CompetenciaRequestDTO> lista = competenciaService.listarCompetenciasDaVaga(vagaId)

        return ResponseEntity.ok(lista)
    }
}
