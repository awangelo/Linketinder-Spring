package com.awangelo.msnucleo.repository

import com.awangelo.msnucleo.model.Vaga
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VagaRepository extends JpaRepository<Vaga, Long> {
}
