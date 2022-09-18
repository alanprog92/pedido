package com.alan.pedido.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.Cliente;
import com.alan.pedido.model.CondPgto;
import com.alan.pedido.model.FormaPgto;
import com.alan.pedido.model.PedidoVenda;
import com.alan.pedido.model.PrazoVencimento;
import com.alan.pedido.model.Vendedor;
import com.alan.pedido.repository.PedidoVendaRepository;

@Service
public class PedidoVendaService {
	
    @Autowired
    PedidoVendaRepository pedidovendaRepository;   
    
    @Autowired
    ClienteService clienteService;    
    
    @Autowired
    CondPgtoService condpgtoService;        
    
    @Autowired
    FormaPgtoService formapgtoService;        
    
    @Autowired
    PrazoVencimentoService prazovencimentoService; 
    
    @Autowired
    VendedorService vendedorService;     
     
	
	public List<PedidoVenda> lista(){
		return pedidovendaRepository.findAll();
	}
	
	public PedidoVenda listaId(Integer pedidovendaId){
		return pedidovendaRepository.findById(pedidovendaId)
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVenda", "id", pedidovendaId));
	}
	
    public PedidoVenda inserir(PedidoVenda pedidovenda) {    
    	
        Cliente cliente = clienteService.listaId(pedidovenda.getCliente().getId());
        
        CondPgto condpgto = condpgtoService.listaId(pedidovenda.getCondpgto().getId());      
        
        FormaPgto formapgto = formapgtoService.listaId(pedidovenda.getFormapgto().getId());
        
        PrazoVencimento prazovencimento = prazovencimentoService.listaId(pedidovenda.getPrazovencimento().getId());
        
		Vendedor vendedor = vendedorService.listaId(pedidovenda.getVendedor().getId()); 
		
		 
        pedidovenda.setCliente(cliente);
        pedidovenda.setCondpgto(condpgto);
        pedidovenda.setFormapgto(formapgto);
        pedidovenda.setPrazovencimento(prazovencimento);
        pedidovenda.setVendedor(vendedor);
		
		
        
        return pedidovendaRepository.save(pedidovenda);
        
    }
	
	public PedidoVenda atualiza(Integer pedidovendaId,PedidoVenda pedidovendaDetails){
 
        PedidoVenda pedidovenda = pedidovendaRepository.findById(pedidovendaId)
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVenda", "id", pedidovendaId));
        
        
        Cliente cliente = clienteService.listaId(pedidovendaDetails.getCliente().getId());
        
        CondPgto condpgto = condpgtoService.listaId(pedidovendaDetails.getCondpgto().getId());      
        
        FormaPgto formapgto = formapgtoService.listaId(pedidovendaDetails.getFormapgto().getId());
        
        PrazoVencimento prazovencimento = prazovencimentoService.listaId(pedidovendaDetails.getPrazovencimento().getId());
        
		Vendedor vendedor = vendedorService.listaId(pedidovendaDetails.getVendedor().getId());        
        
 
        pedidovenda.setId(pedidovendaDetails.getId());        
        pedidovenda.setCliente(cliente);
        pedidovenda.setEmissao(pedidovendaDetails.getEmissao());
        pedidovenda.setStatus(pedidovendaDetails.getStatus());
        pedidovenda.setTotalpedido(pedidovendaDetails.getTotalpedido());
        pedidovenda.setDesconto(pedidovendaDetails.getDesconto());
        pedidovenda.setCondpgto(condpgto);
        pedidovenda.setFormapgto(formapgto);
        pedidovenda.setPrevchegada(pedidovendaDetails.getPrevchegada());
        pedidovenda.setComissao(pedidovendaDetails.getComissao());
        pedidovenda.setPercomissao(pedidovendaDetails.getPercomissao());
        pedidovenda.setTotaldesc(pedidovendaDetails.getTotaldesc());
        pedidovenda.setDatavenc(pedidovendaDetails.getDatavenc());
        pedidovenda.setPrazovencimento(prazovencimento);
        pedidovenda.setTipodesconto(pedidovendaDetails.getTipodesconto());
        pedidovenda.setVendedor(vendedor);
        pedidovenda.setObservacao(pedidovendaDetails.getObservacao());
        pedidovenda.setSincronizado(pedidovendaDetails.getSincronizado());
        pedidovenda.setFase(pedidovendaDetails.getFase());       

        PedidoVenda updatedPedidoVenda = pedidovendaRepository.save(pedidovenda);
        return updatedPedidoVenda;
	}
	
    public ResponseEntity<?> excluir(Integer pedidovendaId) {
        PedidoVenda pedidovenda = pedidovendaRepository.findById(pedidovendaId)
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVenda", "id", pedidovendaId));

        pedidovendaRepository.delete(pedidovenda);

        return ResponseEntity.ok().build();
    }
    
    public List<PedidoVenda> buscaPedidos(
    		Integer vendedorId,
    	    String nomecliente,
    		String status,
    	    String sincronizado,
    		LocalDate dataini,
    	    LocalDate datafim    		
    		) {
    	
        return pedidovendaRepository.BuscaPedidos(vendedorId, nomecliente+"%", 
        		status != "" ? status: "%", 
        		sincronizado != "" ? sincronizado: "%", 
        		dataini, 
        		datafim);
        
    }    

	
	
}
