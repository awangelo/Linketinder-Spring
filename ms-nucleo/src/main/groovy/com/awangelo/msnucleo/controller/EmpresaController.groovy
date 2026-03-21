package com.awangelo.msnucleo.controller

import com.awangelo.msnucleo.dto.EmpresaRequestDTO
import com.awangelo.msnucleo.model.Empresa
import com.awangelo.msnucleo.service.EmpresaService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
class EmpresaController {

    private final EmpresaService empresaService

    EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService
    }

    @PostMapping
    ResponseEntity<Empresa> criarEmpresa(@RequestBody @Valid EmpresaRequestDTO dto) {
        Empresa novaEmpresa = empresaService.criarEmpresa(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEmpresa)
    }

}
