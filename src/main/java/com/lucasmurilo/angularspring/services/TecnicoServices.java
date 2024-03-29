package com.lucasmurilo.angularspring.services;

import com.lucasmurilo.angularspring.entities.DTOS.TecnicoDTO;
import com.lucasmurilo.angularspring.entities.Pessoa;
import com.lucasmurilo.angularspring.entities.Tecnico;
import com.lucasmurilo.angularspring.exceptions.DataIntegrityViolationException;
import com.lucasmurilo.angularspring.exceptions.ObjectNotFoundException;
import com.lucasmurilo.angularspring.repository.PessoaRepository;
import com.lucasmurilo.angularspring.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoServices {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        List<Tecnico> list = tecnicoRepository.findAll();
        return list;
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        validaPorCpfEEmail(tecnicoDTO);
        Tecnico newObj = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(newObj);
    }

    public Tecnico update(Integer id, TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCpfEEmail(tecnicoDTO);
        oldObj = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(oldObj);
    }

    private void validaPorCpfEEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("Cpf já cadastrado no sistema");
        }

        obj = pessoaRepository.findByEmail(tecnicoDTO.getEmail());

        if (obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }

    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("O técnico possui ordem de servico e não pode ser deletado");
        }
        tecnicoRepository.deleteById(id);
    }
}


