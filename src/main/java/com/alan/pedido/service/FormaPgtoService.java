package com.alan.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.FormaPgto;
import com.alan.pedido.repository.FormaPgtoRepository;

@Service
public class FormaPgtoService {
	
    @Autowired
    FormaPgtoRepository formapgtoRepository;  
	
	public List<FormaPgto> lista(){
		return formapgtoRepository.findAll();
	}
	
	public FormaPgto listaId(Integer formapgtoId){
		return formapgtoRepository.findById(formapgtoId)
                .orElseThrow(() -> new ResourceNotFoundException("FormaPgto", "id", formapgtoId));
	}
	
    public FormaPgto inserir(FormaPgto formapgto) {    	
        
        return formapgtoRepository.save(formapgto);
        
    }
	
	public FormaPgto atualiza(Integer formapgtoId,FormaPgto formapgtoDetails){
 
        FormaPgto formapgto = formapgtoRepository.findById(formapgtoId)
                .orElseThrow(() -> new ResourceNotFoundException("FormaPgto", "id", formapgtoId));
 
        formapgto.setId(formapgtoDetails.getId());
        formapgto.setDescricao(formapgtoDetails.getDescricao());

        FormaPgto updatedFormaPgto = formapgtoRepository.save(formapgto);
        return updatedFormaPgto;
	}
	
    public ResponseEntity<?> excluir(Integer formapgtoId) {
        FormaPgto formapgto = formapgtoRepository.findById(formapgtoId)
                .orElseThrow(() -> new ResourceNotFoundException("FormaPgto", "id", formapgtoId));

        formapgtoRepository.delete(formapgto);

        return ResponseEntity.ok().build();
    }
	
	
}
