package com.awangelo.msnucleo.client

import com.awangelo.msnucleo.dto.CompetenciaDTO
import com.awangelo.msnucleo.dto.VagaCompetenciaDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(name = "ms-competencia", url = '${ms.competencia.url}')
interface MsCompetenciaClient {

    @PostMapping("/api/competencias/vagas")
    void enviarCompetenciasVaga(@RequestBody VagaCompetenciaDTO dto)

    @GetMapping("/api/competencias/vagas/{vagaId}")
    List<CompetenciaDTO> getCompetenciasVaga(@PathVariable("vagaId") Long vagaId)

    @GetMapping("/api/competencias/candidatos/{candidatoId}")
    List<CompetenciaDTO> getCompetenciasCandidato(@PathVariable("candidatoId") Long candidatoId)

}
