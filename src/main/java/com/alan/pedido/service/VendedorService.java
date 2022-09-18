package com.alan.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.Vendedor;
import com.alan.pedido.repository.VendedorRepository;

@Service
public class VendedorService {
	
    @Autowired
    VendedorRepository vendedorRepository;  
	
	public List<Vendedor> lista(){
		return vendedorRepository.findAll();
	}
	
	public Vendedor listaId(Integer vendedorId){
		return vendedorRepository.findById(vendedorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor", "id", vendedorId));
	}
	
    public Vendedor inserir(Vendedor vendedor) {    	
        
        return vendedorRepository.save(vendedor);
        
    }
	
	public Vendedor atualiza(Integer vendedorId,Vendedor vendedorDetails){
 
        Vendedor vendedor = vendedorRepository.findById(vendedorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor", "id", vendedorId));
 
        vendedor.setId(vendedorDetails.getId());
        vendedor.setNome(vendedorDetails.getNome());
        vendedor.setTipopessoa(vendedorDetails.getTipopessoa());
        vendedor.setCpf_cnpj(vendedorDetails.getCpf_cnpj());
        vendedor.setFone(vendedorDetails.getFone());
        vendedor.setEndereco(vendedorDetails.getEndereco());
        vendedor.setNumero(vendedorDetails.getNumero());
        vendedor.setBairro(vendedorDetails.getBairro());
        vendedor.setCidade(vendedorDetails.getCidade());
        vendedor.setUf(vendedorDetails.getUf());
        vendedor.setCep(vendedorDetails.getCep());

        Vendedor updatedVendedor = vendedorRepository.save(vendedor);
        return updatedVendedor;
	}
	
    public ResponseEntity<?> excluir(Integer vendedorId) {
        Vendedor vendedor = vendedorRepository.findById(vendedorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor", "id", vendedorId));

        vendedorRepository.delete(vendedor);

        return ResponseEntity.ok().build();
    }
	
	
}
