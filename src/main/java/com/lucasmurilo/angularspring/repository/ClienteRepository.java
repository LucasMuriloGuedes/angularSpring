package com.lucasmurilo.angularspring.repository;

import com.lucasmurilo.angularspring.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
