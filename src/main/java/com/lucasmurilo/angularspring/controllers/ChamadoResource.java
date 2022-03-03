package com.lucasmurilo.angularspring.controllers;

import com.lucasmurilo.angularspring.entities.DTOS.ChamadoDTO;
import com.lucasmurilo.angularspring.entities.DTOS.ClienteDTO;
import com.lucasmurilo.angularspring.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<ChamadoDTO> listDTO = service.findAll().stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        ChamadoDTO objDto = new ChamadoDTO(service.findById(id));
        return ResponseEntity.ok().body(objDto);
    }

}
