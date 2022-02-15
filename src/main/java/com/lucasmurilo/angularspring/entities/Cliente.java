package com.lucasmurilo.angularspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasmurilo.angularspring.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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


    public List<Chamado> getChamados() {
        return chamados;
    }
}
