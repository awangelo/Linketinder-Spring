package com.awangelo.msnucleo.client

import com.awangelo.msnucleo.dto.VagaCompetenciaDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "ms-competencia", url = '${ms.competencia.url}')
interface MsCompetenciaClient {

    @PostMapping("/api/competencias/vagas")
    void enviarCompetenciasVaga(@RequestBody VagaCompetenciaDTO dto)

}
