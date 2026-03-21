package com.awangelo.msnucleo.service

import com.awangelo.msnucleo.dto.EmpresaRequestDTO
import com.awangelo.msnucleo.model.Empresa
import com.awangelo.msnucleo.repository.EmpresaRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmpresaService {

    private final EmpresaRepository empresaRepository
    private final PasswordEncoder passwordEncoder

    EmpresaService(EmpresaRepository empresaRepository, PasswordEncoder passwordEncoder) {
        this.empresaRepository = empresaRepository
        this.passwordEncoder = passwordEncoder
    }

    @Transactional
    Empresa criarEmpresa(EmpresaRequestDTO dto) {
        String senha = passwordEncoder.encode(dto.senha)

        Empresa empresa = new Empresa(
                nome: dto.nome,
                cnpj: dto.cnpj,
                email: dto.email,
                senha: senha,
                descricao: dto.descricao,
                pais: dto.pais,
                estado: dto.estado
        )

        Empresa salvo = empresaRepository.save(empresa)
        return salvo
    }
}
