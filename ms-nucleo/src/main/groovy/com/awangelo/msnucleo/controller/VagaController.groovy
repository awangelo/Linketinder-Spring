package com.awangelo.msnucleo.controller

import com.awangelo.msnucleo.dto.CompetenciaDTO
import com.awangelo.msnucleo.dto.VagaRequestDTO
import com.awangelo.msnucleo.dto.VagaResponseDTO
import com.awangelo.msnucleo.model.Vaga
import com.awangelo.msnucleo.service.VagaService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/vagas")
@CrossOrigin(origins = "*")
class VagaController {

    private final VagaService vagaService

    VagaController(VagaService vagaService) {
        this.vagaService = vagaService
    }

    @PostMapping
    ResponseEntity<Vaga> criarVaga(@RequestBody @Valid VagaRequestDTO dto) {
        Vaga novaVaga = vagaService.criarVaga(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVaga)
    }

    @PostMapping("/{id}/competencias")
    ResponseEntity<Void> adicionarCompetencias(@PathVariable("id") Long id, @RequestBody List<CompetenciaDTO> competencias) {
        vagaService.adicionarCompetencias(id, competencias)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    ResponseEntity<List<VagaResponseDTO>> listarVagas() {
        List<VagaResponseDTO> vagas = vagaService.listarVagas()
        return ResponseEntity.ok(vagas)
    }

}
