package com.alan.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.TabelaProduto;
import com.alan.pedido.model.TabelaProdutoDet;
import com.alan.pedido.repository.TabelaProdutoDetRepository;
import com.alan.pedido.repository.TabelaProdutoRepository;

@Service
public class TabelaProdutoDetService {
	
    @Autowired
    TabelaProdutoDetRepository tabelaprodutodetRepository;  
    
    @Autowired
    TabelaProdutoRepository tabelaprodutoRepository;  
	
	public List<TabelaProdutoDet> lista(){
		return tabelaprodutodetRepository.findAll();
	}
	
	public TabelaProdutoDet listaId(Integer tabelaprodutodetId){
		return tabelaprodutodetRepository.findById(tabelaprodutodetId)
                .orElseThrow(() -> new ResourceNotFoundException("TabelaProdutoDet", "id", tabelaprodutodetId));
	}
	
    public TabelaProdutoDet inserir(TabelaProdutoDet tabelaprodutodet) {    	
        
        TabelaProduto tabelaproduto = tabelaprodutoRepository.findById(tabelaprodutodet.getTabelaproduto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("TabelaProduto", "id", tabelaprodutodet.getTabelaproduto().getId()));        
 
        tabelaprodutodet.setTabelaproduto(tabelaproduto);    	
    	
        return tabelaprodutodetRepository.save(tabelaprodutodet);
        
    }
	
	public TabelaProdutoDet atualiza(Integer tabelaprodutodetId,TabelaProdutoDet tabelaprodutodetDetails){
 
        TabelaProdutoDet tabelaprodutodet = tabelaprodutodetRepository.findById(tabelaprodutodetId)
                .orElseThrow(() -> new ResourceNotFoundException("TabelaProdutoDet", "id", tabelaprodutodetId));
        
        TabelaProduto tabelaproduto = tabelaprodutoRepository.findById(tabelaprodutodetDetails.getTabelaproduto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("TabelaProduto", "id", tabelaprodutodetDetails.getTabelaproduto().getId()));        
 
        tabelaprodutodet.setId(tabelaprodutodetDetails.getId());
        tabelaprodutodet.setTabelaproduto(tabelaproduto);
        tabelaprodutodet.setQtdini(tabelaprodutodetDetails.getQtdini());
        tabelaprodutodet.setQtdfim(tabelaprodutodetDetails.getQtdfim());
        tabelaprodutodet.setPrecounitario(tabelaprodutodetDetails.getPrecounitario());

        TabelaProdutoDet updatedTabelaProdutoDet = tabelaprodutodetRepository.save(tabelaprodutodet);
        return updatedTabelaProdutoDet;
	}
	
    public ResponseEntity<?> excluir(Integer tabelaprodutodetId) {
        TabelaProdutoDet tabelaprodutodet = tabelaprodutodetRepository.findById(tabelaprodutodetId)
                .orElseThrow(() -> new ResourceNotFoundException("TabelaProdutoDet", "id", tabelaprodutodetId));

        tabelaprodutodetRepository.delete(tabelaprodutodet);

        return ResponseEntity.ok().build();
    }
	
	
}
