package com.lucasmurilo.angularspring.repository;

import com.lucasmurilo.angularspring.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository  extends JpaRepository<Pessoa, Integer> {

}
