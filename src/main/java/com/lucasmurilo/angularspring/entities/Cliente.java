package com.lucasmurilo.angularspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasmurilo.angularspring.domain.enums.Perfil;
import com.lucasmurilo.angularspring.entities.DTOS.ClienteDTO;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cliente extends Pessoa implements Serializable {

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {

        setPerfil(Perfil.CLIENTE);

    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public Cliente(ClienteDTO cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.senha = cliente.getSenha();
        this.perfil = cliente.getPerfil().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = cliente.getDataCriacao();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }
}
