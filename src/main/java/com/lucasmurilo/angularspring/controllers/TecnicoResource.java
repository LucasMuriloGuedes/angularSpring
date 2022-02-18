package com.lucasmurilo.angularspring.controllers;

import com.lucasmurilo.angularspring.entities.DTOS.TecnicoDTO;
import com.lucasmurilo.angularspring.entities.Tecnico;
import com.lucasmurilo.angularspring.services.TecnicoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoServices tecnicoServices;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        TecnicoDTO objDto = new TecnicoDTO(tecnicoServices.findById(id));
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = tecnicoServices.findAll();
        List<TecnicoDTO> tecnicosDto = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(tecnicosDto);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid  @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico newObj = tecnicoServices.create(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
