package com.alan.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.TabelaProduto;
import com.alan.pedido.repository.TabelaProdutoRepository;

@Service
public class TabelaProdutoService {
	
    @Autowired
    TabelaProdutoRepository tabelaprodutoRepository;  
	
	public List<TabelaProduto> lista(){
		return tabelaprodutoRepository.findAll();
	}
	
	public TabelaProduto listaId(Integer tabelaprodutoId){
		return tabelaprodutoRepository.findById(tabelaprodutoId)
                .orElseThrow(() -> new ResourceNotFoundException("TabelaProduto", "id", tabelaprodutoId));
	}
	
    public TabelaProduto inserir(TabelaProduto tabelaproduto) {    	
        
        return tabelaprodutoRepository.save(tabelaproduto);
        
    }
	
	public TabelaProduto atualiza(Integer tabelaprodutoId,TabelaProduto tabelaprodutoDetails){
 
        TabelaProduto tabelaproduto = tabelaprodutoRepository.findById(tabelaprodutoId)
                .orElseThrow(() -> new ResourceNotFoundException("TabelaProduto", "id", tabelaprodutoId));
 
        tabelaproduto.setId(tabelaprodutoDetails.getId());
        tabelaproduto.setDescricao(tabelaprodutoDetails.getDescricao());

        TabelaProduto updatedTabelaProduto = tabelaprodutoRepository.save(tabelaproduto);
        return updatedTabelaProduto;
        
	}
	
    public ResponseEntity<?> excluir(Integer tabelaprodutoId) {
        TabelaProduto tabelaproduto = tabelaprodutoRepository.findById(tabelaprodutoId)
                .orElseThrow(() -> new ResourceNotFoundException("TabelaProduto", "id", tabelaprodutoId));

        tabelaprodutoRepository.delete(tabelaproduto);

        return ResponseEntity.ok().build();
    }
	
	
}
