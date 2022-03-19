package com.lucasmurilo.angularspring.services;

import com.lucasmurilo.angularspring.domain.enums.Prioridade;
import com.lucasmurilo.angularspring.domain.enums.Status;
import com.lucasmurilo.angularspring.entities.Chamado;
import com.lucasmurilo.angularspring.entities.Cliente;
import com.lucasmurilo.angularspring.entities.DTOS.ChamadoDTO;
import com.lucasmurilo.angularspring.entities.Tecnico;
import com.lucasmurilo.angularspring.exceptions.ObjectNotFoundException;
import com.lucasmurilo.angularspring.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private TecnicoServices tecnicoServices;

    @Autowired
    private ClienteServices clienteServices;


    public Chamado findById(Integer id){
        Optional<Chamado> chamado = repository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id: " + id));
    }

    public List<Chamado> findAll(){
        List<Chamado> list = repository.findAll();
        return list;
    }

    public Chamado create(ChamadoDTO chamadoDTO) {
        return repository.save(newChamado(chamadoDTO));
    }

    public Chamado update(Integer id, ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(chamadoDTO);
        return repository.save(oldObj);
    }

    private Chamado newChamado(ChamadoDTO obj){
        Tecnico tecnico = tecnicoServices.findById(obj.getTecnico());
        Cliente cliente = clienteServices.findByID(obj.getCliente());

        Chamado chamado = new Chamado();
        if(obj.getId() != null){
            chamado.setId(obj.getId());
        }

        if(obj.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;



    }
}
