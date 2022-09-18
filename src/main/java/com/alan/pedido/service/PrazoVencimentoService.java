package com.alan.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.PrazoVencimento;
import com.alan.pedido.repository.PrazoVencimentoRepository;

@Service
public class PrazoVencimentoService {
	
    @Autowired
    PrazoVencimentoRepository prazovencimentoRepository;  
	
	public List<PrazoVencimento> lista(){
		return prazovencimentoRepository.findAll();
	}
	
	public PrazoVencimento listaId(Integer prazovencimentoId){
		return prazovencimentoRepository.findById(prazovencimentoId)
                .orElseThrow(() -> new ResourceNotFoundException("PrazoVencimento", "id", prazovencimentoId));
	}
	
    public PrazoVencimento inserir(PrazoVencimento prazovencimento) {    	
        
        return prazovencimentoRepository.save(prazovencimento);
        
    }
	
	public PrazoVencimento atualiza(Integer prazovencimentoId,PrazoVencimento prazovencimentoDetails){
 
        PrazoVencimento prazovencimento = prazovencimentoRepository.findById(prazovencimentoId)
                .orElseThrow(() -> new ResourceNotFoundException("PrazoVencimento", "id", prazovencimentoId));
 
        prazovencimento.setId(prazovencimentoDetails.getId());
        prazovencimento.setDescricao(prazovencimentoDetails.getDescricao());

        PrazoVencimento updatedPrazoVencimento = prazovencimentoRepository.save(prazovencimento);
        return updatedPrazoVencimento;
	}
	
    public ResponseEntity<?> excluir(Integer prazovencimentoId) {
        PrazoVencimento prazovencimento = prazovencimentoRepository.findById(prazovencimentoId)
                .orElseThrow(() -> new ResourceNotFoundException("PrazoVencimento", "id", prazovencimentoId));

        prazovencimentoRepository.delete(prazovencimento);

        return ResponseEntity.ok().build();
    }
	
	
}
