package com.lucasmurilo.angularspring.config;

import com.lucasmurilo.angularspring.domain.enums.Perfil;
import com.lucasmurilo.angularspring.domain.enums.Prioridade;
import com.lucasmurilo.angularspring.domain.enums.Status;
import com.lucasmurilo.angularspring.entities.Chamado;
import com.lucasmurilo.angularspring.entities.Cliente;
import com.lucasmurilo.angularspring.entities.Tecnico;
import com.lucasmurilo.angularspring.repository.ChamadoRepository;
import com.lucasmurilo.angularspring.repository.ClienteRepository;
import com.lucasmurilo.angularspring.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Override
    public void run(String... args) throws Exception {


    }
}
