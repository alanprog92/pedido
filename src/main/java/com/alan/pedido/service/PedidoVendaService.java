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
import com.alan.pedido.repository.ClienteRepository;
import com.alan.pedido.repository.CondPgtoRepository;
import com.alan.pedido.repository.FormaPgtoRepository;
import com.alan.pedido.repository.PedidoVendaDetRepository;
import com.alan.pedido.repository.PedidoVendaRepository;
import com.alan.pedido.repository.PrazoVencimentoRepository;
import com.alan.pedido.repository.VendedorRepository;

@Service
public class PedidoVendaService {
	
    @Autowired
    PedidoVendaRepository pedidovendaRepository;
    
    @Autowired
    PedidoVendaDetRepository pedidovendadetRepository;    
    
    @Autowired
    ClienteRepository clienteRepository;    
    
    @Autowired
    CondPgtoRepository condpgtoRepository;        
    
    @Autowired
    FormaPgtoRepository formapgtoRepository;        
    
    @Autowired
    PrazoVencimentoRepository prazovencimentoRepository; 
    
    @Autowired
    VendedorRepository vendedorRepository;     
     
	
	public List<PedidoVenda> lista(){
		return pedidovendaRepository.findAll();
	}
	
	public PedidoVenda listaId(Integer pedidovendaId){
		return pedidovendaRepository.findById(pedidovendaId)
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVenda", "id", pedidovendaId));
	}
	
    public PedidoVenda inserir(PedidoVenda pedidovenda) {    	
        
        return pedidovendaRepository.save(pedidovenda);
        
    }
	
	public PedidoVenda atualiza(Integer pedidovendaId,PedidoVenda pedidovendaDetails){
 
        PedidoVenda pedidovenda = pedidovendaRepository.findById(pedidovendaId)
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVenda", "id", pedidovendaId));
        
        
        Cliente cliente = clienteRepository.findById(pedidovendaDetails.getCliente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", pedidovendaDetails.getCliente().getId()));
        
        CondPgto condpgto = condpgtoRepository.findById(pedidovendaDetails.getCondpgto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("CondPgto", "id", pedidovendaDetails.getCondpgto().getId()));        
        
        FormaPgto formapgto = formapgtoRepository.findById(pedidovendaDetails.getFormapgto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("FormaPgto", "id", pedidovendaDetails.getFormapgto().getId()));
        
        PrazoVencimento prazovencimento = prazovencimentoRepository.findById(pedidovendaDetails.getPrazovencimento().getId())
                .orElseThrow(() -> new ResourceNotFoundException("PrazoVencimento", "id", pedidovendaDetails.getPrazovencimento().getId()));
        
		Vendedor vendedor = vendedorRepository.findById(pedidovendaDetails.getVendedor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor", "id", pedidovendaDetails.getVendedor().getId()));           
        
 
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
