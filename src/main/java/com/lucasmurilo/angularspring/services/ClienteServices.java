package com.lucasmurilo.angularspring.services;

import com.lucasmurilo.angularspring.entities.Cliente;
import com.lucasmurilo.angularspring.entities.DTOS.ClienteDTO;
import com.lucasmurilo.angularspring.entities.Pessoa;
import com.lucasmurilo.angularspring.exceptions.DataIntegrityViolationException;
import com.lucasmurilo.angularspring.exceptions.ObjectNotFoundException;
import com.lucasmurilo.angularspring.repository.ClienteRepository;
import com.lucasmurilo.angularspring.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Cliente> findAll(){
        List<Cliente> clientes = repository.findAll();
        return clientes;
    }

    public Cliente findByID(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não Encontrado! Id: " + id));
    }

    public Cliente create(ClienteDTO clienteDTO){
        clienteDTO.setId(null);
        validaPorCpfEmail(clienteDTO);
        Cliente newCliente = new Cliente(clienteDTO);
        return repository.save(newCliente);
    }

    public Cliente update(Integer id, ClienteDTO clienteDTO){
        clienteDTO.setId(id);
        Cliente oldObj = findByID(id);
        validaPorCpfEmail(clienteDTO);
        oldObj = new Cliente(clienteDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Cliente cliente = findByID(id);
        if(cliente.getChamados().size() > 0){
            throw new DataIntegrityViolationException("O Cliente possui ordem de serviços e não pode ser deletado");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEmail(ClienteDTO clienteDTO){

        Optional<Pessoa> obj = pessoaRepository.findByCpf(clienteDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }

        obj = pessoaRepository.findByEmail(clienteDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado!");
        }
    }


}
