package com.alan.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.Produto;
import com.alan.pedido.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
    @Autowired
    ProdutoRepository produtoRepository;  
	
	public List<Produto> lista(){
		return produtoRepository.findAll();
	}
	
	public Produto listaId(Integer produtoId){
		return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "id", produtoId));
	}
	
    public Produto inserir(Produto produto) {    	
        
        return produtoRepository.save(produto);
        
    }
	
	public Produto atualiza(Integer produtoId,Produto produtoDetails){
 
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "id", produtoId));
 
        produto.setId(produtoDetails.getId());
        produto.setDescricao(produtoDetails.getDescricao());

        Produto updatedProduto = produtoRepository.save(produto);
        return updatedProduto;
	}
	
    public ResponseEntity<?> excluir(Integer produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "id", produtoId));

        produtoRepository.delete(produto);

        return ResponseEntity.ok().build();
    }
	
	
}
