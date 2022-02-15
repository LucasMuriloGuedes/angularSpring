package com.lucasmurilo.angularspring.config;

import com.lucasmurilo.angularspring.domain.enums.Perfil;
import com.lucasmurilo.angularspring.domain.enums.Prioridade;
import com.lucasmurilo.angularspring.domain.enums.Status;
import com.lucasmurilo.angularspring.entities.Chamado;
import com.lucasmurilo.angularspring.entities.Cliente;
import com.lucasmurilo.angularspring.entities.Tecnico;
import com.lucasmurilo.angularspring.repository.ChamadoRepository;
import com.lucasmurilo.angularspring.repository.ClienteRepository;
import com.lucasmurilo.angularspring.repository.PessoaRepository;
import com.lucasmurilo.angularspring.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Override
    public void run(String... args) throws Exception {

        Tecnico tecnico1 = new Tecnico(null, "Lucas Murilo", "02308639148", "lucasmurilo.guedes@gmail.com", "123456");
        tecnico1.setPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Abel Ferreira", "1234564541", "abelferreira@palmeiras.com.br", "14455444");

        Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "PrimeiroChamado", tecnico1, cli1);

        tecnico1.getChamados().add(chamado1);

        cli1.getChamados().add(chamado1);

        tecnicoRepository.save(tecnico1);
        clienteRepository.save(cli1);
        chamadoRepository.save(chamado1);


    }
}
