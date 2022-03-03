package com.lucasmurilo.angularspring.entities.DTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasmurilo.angularspring.domain.enums.Perfil;
import com.lucasmurilo.angularspring.entities.Cliente;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTO {

    protected Integer id;
    @NotNull(message = "O campo nome é requerido!")
    protected String nome;
    @NotNull(message = "O Campo CPF é requerido!")
    protected String cpf;
    @NotNull(message = "o campo email é requerido!")
    protected String email;
    @NotNull(message = "O camppo email é requerido!")
    protected String senha;
    protected Set<Integer> perfil = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();


    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getCpf();
        this.senha = cliente.getSenha();
        this.perfil = cliente.getPerfil().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = cliente.getDataCriacao();
        setPerfil(Perfil.CLIENTE);

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Set<Perfil> getPerfil() {
        return perfil.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void setPerfil(Perfil perfil) {
        this.perfil.add(perfil.getCodigo());
    }
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
