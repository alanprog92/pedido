package com.alan.pedido.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.PedidoVenda;
import com.alan.pedido.model.PedidoVendaDet;
import com.alan.pedido.model.Produto;
import com.alan.pedido.model.TabelaProduto;
import com.alan.pedido.repository.PedidoVendaDetRepository;
import com.alan.pedido.repository.PedidoVendaRepository;
import com.alan.pedido.repository.ProdutoRepository;
import com.alan.pedido.repository.TabelaProdutoRepository;

@Service
public class PedidoVendaDetService {
	
    @Autowired
    PedidoVendaDetRepository pedidovendadetRepository;
    
    @Autowired
    PedidoVendaRepository pedidovendaRepository;    
    
    @Autowired
    ProdutoRepository produtoRepository;        
    
    @Autowired
    TabelaProdutoRepository tabelaprodutoRepository;

	
	public List<PedidoVendaDet> lista(){
		return pedidovendadetRepository.findAll();
	}
	
    public List<PedidoVendaDet> buscaItensPedidos( Integer pedidovendaId	) {
        return pedidovendadetRepository.BuscaItensPedido(pedidovendaId);
    }   
	
    public PedidoVenda atuValores( Integer pedidovendaId) {
 
        
        PedidoVenda pedidovenda = pedidovendaRepository.findById(pedidovendaId)
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVenda", "id", pedidovendaId));       

        List<PedidoVendaDet> itens = new ArrayList<PedidoVendaDet>();
        
        itens = pedidovendadetRepository.BuscaItensPedido(pedidovendaId);
        
        Double total = 0.00;
        Double desconto = 0.00;
        Double totaldesc = 0.00;
        
        for (PedidoVendaDet item : itens) {
        	total = total + item.getTotal();
        	desconto = desconto + item.getDesconto();
        	totaldesc = totaldesc + item.getTotaldesc();        	
		}
        
        pedidovenda.setTotalpedido(total);
        pedidovenda.setDesconto(desconto);
        pedidovenda.setTotaldesc(totaldesc);
        
        PedidoVenda updatedPedidoVenda = pedidovendaRepository.save(pedidovenda);
        return updatedPedidoVenda;
    }  
	
	public PedidoVendaDet listaId(Integer pedidovendadetId){
		return pedidovendadetRepository.findById(pedidovendadetId)
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVendaDet", "id", pedidovendadetId));
	}
	
    public PedidoVendaDet inserir(PedidoVendaDet pedidovendadet) {    	
        
        PedidoVenda pedidovenda = pedidovendaRepository.findById(pedidovendadet.getPedidovenda().getId())
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVenda", "id", pedidovendadet.getPedidovenda().getId()));                
        
        Produto produto = produtoRepository.findById(pedidovendadet.getProduto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "id", pedidovendadet.getProduto().getId()));
        
        if(pedidovendadet.getTabelaproduto() != null) {
	        
	        TabelaProduto tabelaproduto = tabelaprodutoRepository.findById(pedidovendadet.getTabelaproduto().getId())
	                .orElseThrow(() -> new ResourceNotFoundException("TabelaProduto", "id", pedidovendadet.getTabelaproduto().getId()));               
 
	        pedidovendadet.setTabelaproduto(tabelaproduto);
	        
        }
    
        pedidovendadet.setPedidovenda(pedidovenda);
        pedidovendadet.setProduto(produto);

    	
        return pedidovendadetRepository.save(pedidovendadet);
        
    }
	
	public PedidoVendaDet atualiza(Integer pedidovendadetId,PedidoVendaDet pedidovendadetDetails){
 
        PedidoVendaDet pedidovendadet = pedidovendadetRepository.findById(pedidovendadetDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVenda", "id", pedidovendadetDetails.getId()));      
        
        PedidoVenda pedidovenda = pedidovendaRepository.findById(pedidovendadetDetails.getPedidovenda().getId())
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVenda", "id", pedidovendadetDetails.getPedidovenda().getId()));                
        
        Produto produto = produtoRepository.findById(pedidovendadetDetails.getProduto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "id", pedidovendadetDetails.getProduto().getId()));
        
        TabelaProduto tabelaproduto = tabelaprodutoRepository.findById(pedidovendadetDetails.getTabelaproduto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("TabelaProduto", "id", pedidovendadetDetails.getTabelaproduto().getId()));               
 
        pedidovendadet.setId(pedidovendadetDetails.getId());        
        pedidovendadet.setPedidovenda(pedidovenda);
        pedidovendadet.setProduto(produto);
        pedidovendadet.setUnidade(pedidovendadetDetails.getUnidade());
        pedidovendadet.setQuantidade(pedidovendadetDetails.getQuantidade());
        pedidovendadet.setUnitario(pedidovendadetDetails.getUnitario());
        pedidovendadet.setTotal(pedidovendadetDetails.getTotal());
        pedidovendadet.setStatus(pedidovendadetDetails.getStatus());
        pedidovendadet.setPercomissao(pedidovendadetDetails.getPercomissao());
        pedidovendadet.setComissao(pedidovendadetDetails.getComissao());
        pedidovendadet.setDesconto(pedidovendadetDetails.getDesconto());
        pedidovendadet.setTotaldesc(pedidovendadetDetails.getTotaldesc());
        pedidovendadet.setTipovenda(pedidovendadetDetails.getTipovenda());
        pedidovendadet.setTipodesconto(pedidovendadetDetails.getTipodesconto());
        pedidovendadet.setDescontoaux(pedidovendadetDetails.getDescontoaux());
        pedidovendadet.setTabelaproduto(tabelaproduto);

        PedidoVendaDet updatedPedidoVendaDet = pedidovendadetRepository.save(pedidovendadet);
        return updatedPedidoVendaDet;
	}
	
    public ResponseEntity<?> excluir(Integer pedidovendadetId) {
        PedidoVendaDet pedidovendadet = pedidovendadetRepository.findById(pedidovendadetId)
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVendaDet", "id", pedidovendadetId));

        pedidovendadetRepository.delete(pedidovendadet);

        return ResponseEntity.ok().build();
    }
	
	
}
