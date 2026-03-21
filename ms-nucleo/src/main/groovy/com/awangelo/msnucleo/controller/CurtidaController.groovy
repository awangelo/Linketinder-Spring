package com.awangelo.msnucleo.controller

import com.awangelo.msnucleo.dto.CandidatoResponseDTO
import com.awangelo.msnucleo.dto.CurtidaRequestDTO
import com.awangelo.msnucleo.dto.VagaResponseDTO
import com.awangelo.msnucleo.service.CurtidaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/curtidas")
@CrossOrigin(origins = "*")
class CurtidaController {

    private final CurtidaService curtidaService

    CurtidaController(CurtidaService curtidaService) {
        this.curtidaService = curtidaService
    }

    @PostMapping
    ResponseEntity<String> curtir(@RequestBody @Valid CurtidaRequestDTO dto) {
        boolean match = curtidaService.processarCurtida(dto)

        if (match) {
            return ResponseEntity.ok("Match!")
        } 
        
        return ResponseEntity.ok("Curtida registrada com sucesso!")
    }

    @GetMapping("/matches/candidatos/{candidatoId}")
    ResponseEntity<List<VagaResponseDTO>> listarMatchesCandidato(@PathVariable("candidatoId") Long candidatoId) {
        List<VagaResponseDTO> matches = curtidaService.listarMatchesCandidato(candidatoId)
        return ResponseEntity.ok(matches)
    }

    @GetMapping("/matches/vagas/{vagaId}")
    ResponseEntity<List<CandidatoResponseDTO>> listarMatchesVaga(@PathVariable("vagaId") Long vagaId) {
        List<CandidatoResponseDTO> matches = curtidaService.listarMatchesVaga(vagaId)
        return ResponseEntity.ok(matches)
    }

}
