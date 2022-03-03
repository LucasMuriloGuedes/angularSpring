package com.lucasmurilo.angularspring.controllers;

import com.lucasmurilo.angularspring.entities.DTOS.ClienteDTO;
import com.lucasmurilo.angularspring.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteServices services;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> objDTO = services.findAll().stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        ClienteDTO clienteDTO = new ClienteDTO(services.findByID(id));
        return ResponseEntity.ok().body(clienteDTO);
    }

    @PostMapping()
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO obj){
        ClienteDTO objDto = new ClienteDTO(services.create(obj));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO){
        ClienteDTO clienteDTO = new ClienteDTO(services.update(id, objDTO));
        return ResponseEntity.ok().body(clienteDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
