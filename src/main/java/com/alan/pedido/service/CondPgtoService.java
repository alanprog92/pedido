package com.alan.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.CondPgto;
import com.alan.pedido.repository.CondPgtoRepository;

@Service
public class CondPgtoService {
	
    @Autowired
    CondPgtoRepository condpgtoRepository;  
	
	public List<CondPgto> lista(){
		return condpgtoRepository.findAll();
	}
	
	public CondPgto listaId(Integer condpgtoId){
		return condpgtoRepository.findById(condpgtoId)
                .orElseThrow(() -> new ResourceNotFoundException("CondPgto", "id", condpgtoId));
	}
	
    public CondPgto inserir(CondPgto condpgto) {    	
        
        return condpgtoRepository.save(condpgto);
        
    }
	
	public CondPgto atualiza(Integer condpgtoId,CondPgto condpgtoDetails){
 
		CondPgto condpgto = condpgtoRepository.findById(condpgtoId)
                .orElseThrow(() -> new ResourceNotFoundException("CondPgto", "id", condpgtoId));
 
        condpgto.setId(condpgtoDetails.getId());
        condpgto.setCodigo(condpgtoDetails.getCodigo());
        condpgto.setDescricao(condpgtoDetails.getDescricao());

        CondPgto updatedCondPgto = condpgtoRepository.save(condpgto);
        return updatedCondPgto;
	}
	
    public ResponseEntity<?> excluir(Integer condpgtoId) {
        CondPgto condpgto = condpgtoRepository.findById(condpgtoId)
                .orElseThrow(() -> new ResourceNotFoundException("CondPgto", "id", condpgtoId));

        condpgtoRepository.delete(condpgto);

        return ResponseEntity.ok().build();
    }
	
	
}
