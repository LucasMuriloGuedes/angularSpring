package com.lucasmurilo.angularspring.repository;

import com.lucasmurilo.angularspring.entities.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
