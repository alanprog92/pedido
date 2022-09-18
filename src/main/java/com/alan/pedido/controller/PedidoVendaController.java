package com.alan.pedido.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alan.pedido.model.PedidoVenda;
import com.alan.pedido.service.PedidoVendaService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class PedidoVendaController {

    @Autowired
    PedidoVendaService pedidovendaService;
    
    @GetMapping("/pedidovenda")
    public List<PedidoVenda> getBuscaPedidos(
    		@RequestParam(value = "vendedorid") Integer vendedorId,
    		@RequestParam(value = "cliente") String nomecliente,
    		@RequestParam(value = "status") String status,
    		@RequestParam(value = "sinc") String sincronizado,
    		@RequestParam(value = "dataini") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataini,
    		@RequestParam(value = "datafim") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate datafim    		
    		) {
    	
        return pedidovendaService.buscaPedidos(vendedorId, nomecliente,	status, sincronizado, dataini, datafim);
        
    }    

    @PostMapping("/pedidovenda")
    public PedidoVenda createPedidoVenda(@Valid @RequestBody PedidoVenda pedidovenda) {
    	
        return pedidovendaService.inserir(pedidovenda);
        
    }

    @GetMapping("/pedidovenda/{id}")
    public PedidoVenda getPedidoVendaById(@PathVariable(value = "id") Integer pedidovendaId) {
        return pedidovendaService.listaId(pedidovendaId);
    }

    @PutMapping("/pedidovenda/{id}")
    public PedidoVenda updatePedidoVenda(@PathVariable(value = "id") Integer pedidovendaId,
                                           @Valid @RequestBody PedidoVenda pedidovendaDetails) { 

        return pedidovendaService.atualiza(pedidovendaId, pedidovendaDetails);

    }

    @DeleteMapping("/pedidovenda/{id}")
    public ResponseEntity<?> deletePedidoVenda(@PathVariable(value = "id") Integer pedidovendaId) {
    	
        pedidovendaService.excluir(pedidovendaId);

        return ResponseEntity.ok().build();
    }
    
}
