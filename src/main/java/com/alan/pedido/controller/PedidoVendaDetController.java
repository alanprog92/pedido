package com.alan.pedido.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.alan.pedido.model.PedidoVendaDet;
import com.alan.pedido.service.PedidoVendaDetService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class PedidoVendaDetController {

    @Autowired
    PedidoVendaDetService pedidovendadetService;
    

    @PostMapping("/pedidovendadet")
    public PedidoVendaDet createPedidoVendaDet(@Valid @RequestBody PedidoVendaDet pedidovendadet) {
    	
        return pedidovendadetService.inserir(pedidovendadet);
        
    }

    @GetMapping("/pedidovendadet/{id}")
    public PedidoVendaDet getPedidoVendaDetById(@PathVariable(value = "id") Integer pedidovendadetId) {
        return pedidovendadetService.listaId(pedidovendadetId);
    }

    @GetMapping("/pedidovendadet")
    public List<PedidoVendaDet> getBuscaItensPedidos(
    		@RequestParam(value = "pedidovendaid") Integer pedidovendaId
    		) {
        return pedidovendadetService.buscaItensPedidos(pedidovendaId);
    }   
    
    
    @PutMapping("/pedidovendadet/atuvalores/{id}")
    public PedidoVenda atuValores(@PathVariable(value = "id") Integer pedidovendaId) {
    	
        return pedidovendadetService.atuValores(pedidovendaId);

    }    
    
    
    @PutMapping("/pedidovendadet/{id}")
    public PedidoVendaDet updatePedidoVendaDet(@PathVariable(value = "id") Integer pedidovendadetId,
                                           @Valid @RequestBody PedidoVendaDet pedidovendadetDetails) {

        return pedidovendadetService.atualiza(pedidovendadetId, pedidovendadetDetails);
  
    }

    @DeleteMapping("/pedidovendadet/{id}")
    public ResponseEntity<?> deletePedidoVendaDet(@PathVariable(value = "id") Integer pedidovendadetId) {

        pedidovendadetService.excluir(pedidovendadetId);

        return ResponseEntity.ok().build();
        
    }
    
}
