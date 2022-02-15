package com.lucasmurilo.angularspring.repository;

import com.lucasmurilo.angularspring.entities.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
