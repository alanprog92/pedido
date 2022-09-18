package com.alan.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.Cliente;
import com.alan.pedido.model.Vendedor;
import com.alan.pedido.repository.ClienteRepository;

@Service
public class ClienteService {
	
    @Autowired
    ClienteRepository clienteRepository;
    
    @Autowired
    VendedorService vendedorService;    
	
	public List<Cliente> lista(){
		return clienteRepository.findAll();
	}
	
	public List<Cliente> listaNome(String nome){
		return clienteRepository.BuscaClientes(nome+"%");
	}
	
	public Cliente listaId(Integer clienteId){
		return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));
	}
	
    public Cliente inserir(Cliente cliente) {
    	
        Vendedor vendedor = vendedorService.listaId(cliente.getVendedor().getId());   
        
        cliente.setVendedor(vendedor);
        
        return clienteRepository.save(cliente);
    }
	
	public Cliente atualiza(Integer clienteId,Cliente clienteDetails){
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));
        
        Vendedor vendedor = vendedorService.listaId(cliente.getVendedor().getId());        
 
        cliente.setId(clienteDetails.getId());
        cliente.setNome(clienteDetails.getNome());
        cliente.setTipopessoa(clienteDetails.getTipopessoa());
        cliente.setCpf_cnpj(clienteDetails.getCpf_cnpj());
        cliente.setFone(clienteDetails.getFone());
        cliente.setEndereco(clienteDetails.getEndereco());
        cliente.setNumero(clienteDetails.getNumero());
        cliente.setBairro(clienteDetails.getBairro());
        cliente.setCidade(clienteDetails.getCidade());
        cliente.setUf(clienteDetails.getUf());
        cliente.setCep(clienteDetails.getCep());
        cliente.setCodigoERP(clienteDetails.getCodigoERP());
        cliente.setVendedor(vendedor);

        Cliente updatedCliente = clienteRepository.save(cliente);
        return updatedCliente;
	}
	
    public ResponseEntity<?> excluir(Integer clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));

        clienteRepository.delete(cliente);

        return ResponseEntity.ok().build();
    }
	
	
}
