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

@Service
public class PedidoVendaDetService {
	
    @Autowired
    PedidoVendaDetRepository pedidovendadetRepository;
    
    @Autowired
    PedidoVendaService pedidovendaService;    
    
    @Autowired
    ProdutoService produtoService;        
    
    @Autowired
    TabelaProdutoService tabelaprodutoService;

	
	public List<PedidoVendaDet> lista(){
		return pedidovendadetRepository.findAll();
	}
	
    public List<PedidoVendaDet> buscaItensPedidos( Integer pedidovendaId	) {
        return pedidovendadetRepository.BuscaItensPedido(pedidovendaId);
    }   
	
    public PedidoVenda atuValores( Integer pedidovendaId) {
 
        
        PedidoVenda pedidovenda = pedidovendaService.listaId(pedidovendaId);       

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
        
        PedidoVenda updatedPedidoVenda = pedidovendaService.atualiza(pedidovendaId, pedidovenda);
        return updatedPedidoVenda;
    }  
	
	public PedidoVendaDet listaId(Integer pedidovendadetId){
		return pedidovendadetRepository.findById(pedidovendadetId)
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVendaDet", "id", pedidovendadetId));
	}
	
    public PedidoVendaDet inserir(PedidoVendaDet pedidovendadet) {    	
        
        PedidoVenda pedidovenda = pedidovendaService.listaId(pedidovendadet.getPedidovenda().getId());               
        
        Produto produto = produtoService.listaId(pedidovendadet.getProduto().getId());
        
        if(pedidovendadet.getTabelaproduto() != null) {
	        
	        TabelaProduto tabelaproduto = tabelaprodutoService.listaId(pedidovendadet.getTabelaproduto().getId());               
 
	        pedidovendadet.setTabelaproduto(tabelaproduto);
	        
        }
    
        pedidovendadet.setPedidovenda(pedidovenda);
        pedidovendadet.setProduto(produto);

    	
        return pedidovendadetRepository.save(pedidovendadet);
        
    }
	
	public PedidoVendaDet atualiza(Integer pedidovendadetId,PedidoVendaDet pedidovendadetDetails){
 
        PedidoVendaDet pedidovendadet = pedidovendadetRepository.findById(pedidovendadetDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("PedidoVenda", "id", pedidovendadetDetails.getId()));      
        
        PedidoVenda pedidovenda = pedidovendaService.listaId(pedidovendadetDetails.getPedidovenda().getId());                
        
        Produto produto = produtoService.listaId(pedidovendadetDetails.getProduto().getId());
        
        TabelaProduto tabelaproduto = tabelaprodutoService.listaId(pedidovendadetDetails.getTabelaproduto().getId());               
 
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
