package com.lucasmurilo.angularspring.services;

import com.lucasmurilo.angularspring.entities.Chamado;
import com.lucasmurilo.angularspring.exceptions.ObjectNotFoundException;
import com.lucasmurilo.angularspring.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id){
        Optional<Chamado> chamado = repository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id: " + id));
    }

    public List<Chamado> findAll(){
        List<Chamado> list = repository.findAll();
        return list;
    }
}
