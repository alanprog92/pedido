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
import org.springframework.web.bind.annotation.RestController;

import com.alan.pedido.model.TabelaProdutoDet;
import com.alan.pedido.service.TabelaProdutoDetService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class TabelaProdutoDetController {

    @Autowired
    TabelaProdutoDetService tabelaprodutodetService;  

    @GetMapping("/tabelaprodutodets")
    public List<TabelaProdutoDet> getAllTabelaProdutoDets() {
        return tabelaprodutodetService.lista();
    }

    @PostMapping("/tabelaprodutodets")
    public TabelaProdutoDet createTabelaProdutoDet(@Valid @RequestBody TabelaProdutoDet tabelaprodutodet) {
    	
        return tabelaprodutodetService.inserir(tabelaprodutodet);
    }

    @GetMapping("/tabelaprodutodets/{id}")
    public TabelaProdutoDet getTabelaProdutoDetById(@PathVariable(value = "id") Integer tabelaprodutodetId) {
        return tabelaprodutodetService.listaId(tabelaprodutodetId);
    }

    @PutMapping("/tabelaprodutodets/{id}")
    public TabelaProdutoDet updateTabelaProdutoDet(@PathVariable(value = "id") Integer tabelaprodutodetId,
                                           @Valid @RequestBody TabelaProdutoDet tabelaprodutodetDetails) {

        return tabelaprodutodetService.atualiza(tabelaprodutodetId, tabelaprodutodetDetails);
    }

    @DeleteMapping("/tabelaprodutodets/{id}")
    public ResponseEntity<?> deleteTabelaProdutoDet(@PathVariable(value = "id") Integer tabelaprodutodetId) {

        tabelaprodutodetService.excluir(tabelaprodutodetId);

        return ResponseEntity.ok().build();
    }
    
}
