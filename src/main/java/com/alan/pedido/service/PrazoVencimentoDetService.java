package com.alan.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.PrazoVencimento;
import com.alan.pedido.model.PrazoVencimentoDet;
import com.alan.pedido.repository.PrazoVencimentoDetRepository;
import com.alan.pedido.repository.PrazoVencimentoRepository;

@Service
public class PrazoVencimentoDetService {
	
    @Autowired
    PrazoVencimentoDetRepository prazovencimentodetRepository;  
    
    @Autowired
    PrazoVencimentoRepository prazovencimentoRepository;      
	
	public List<PrazoVencimentoDet> lista(){
		return prazovencimentodetRepository.findAll();
	}
	
	public PrazoVencimentoDet listaId(Integer prazovencimentodetId){
		return prazovencimentodetRepository.findById(prazovencimentodetId)
                .orElseThrow(() -> new ResourceNotFoundException("PrazoVencimentoDet", "id", prazovencimentodetId));
	}
	
    public PrazoVencimentoDet inserir(PrazoVencimentoDet prazovencimentodet) {    	
        
        PrazoVencimento prazovencimento = prazovencimentoRepository.findById(prazovencimentodet.getPrazovencimento().getId())
                .orElseThrow(() -> new ResourceNotFoundException("PrazoVencimento", "id", prazovencimentodet.getPrazovencimento().getId()));

        prazovencimentodet.setPrazovencimento(prazovencimento); 
    	
        return prazovencimentodetRepository.save(prazovencimentodet);
              
    }
	
	public PrazoVencimentoDet atualiza(Integer prazovencimentodetId,PrazoVencimentoDet prazovencimentodetDetails){
 
        PrazoVencimentoDet prazovencimentodet = prazovencimentodetRepository.findById(prazovencimentodetId)
                .orElseThrow(() -> new ResourceNotFoundException("PrazoVencimentoDet", "id", prazovencimentodetId));
        
        PrazoVencimento prazovencimento = prazovencimentoRepository.findById(prazovencimentodetDetails.getPrazovencimento().getId())
                .orElseThrow(() -> new ResourceNotFoundException("PrazoVencimentoDet", "id", prazovencimentodetId));
 
        prazovencimentodet.setId(prazovencimentodetDetails.getId());
        prazovencimentodet.setPrazovencimento(prazovencimento);
        prazovencimentodet.setCodigo(prazovencimentodetDetails.getCodigo());
        prazovencimentodet.setQtddias(prazovencimentodetDetails.getQtddias());        

        PrazoVencimentoDet updatedPrazoVencimentoDet = prazovencimentodetRepository.save(prazovencimentodet);
        return updatedPrazoVencimentoDet;
	}
	
    public ResponseEntity<?> excluir(Integer prazovencimentodetId) {
        PrazoVencimentoDet prazovencimentodet = prazovencimentodetRepository.findById(prazovencimentodetId)
                .orElseThrow(() -> new ResourceNotFoundException("PrazoVencimentoDet", "id", prazovencimentodetId));

        prazovencimentodetRepository.delete(prazovencimentodet);

        return ResponseEntity.ok().build();
    }
	
	
}
