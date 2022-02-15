package com.lucasmurilo.angularspring.controllers;

import com.lucasmurilo.angularspring.entities.Tecnico;
import com.lucasmurilo.angularspring.services.TecnicoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoServices tecnicoServices;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tecnico> findById(@PathVariable Integer id){
        Tecnico tecnico = tecnicoServices.findById(id);
        return ResponseEntity.ok().body(tecnico);

    }

}
