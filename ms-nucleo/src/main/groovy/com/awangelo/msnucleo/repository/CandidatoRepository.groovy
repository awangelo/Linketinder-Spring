package com.awangelo.msnucleo.repository

import com.awangelo.msnucleo.model.Candidato
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    Optional<Candidato> findByEmail(String email)
    Optional<Candidato> findByCpf(String cpf)
    boolean existsByEmail(String email)
    boolean existsByCpf(String cpf)

}