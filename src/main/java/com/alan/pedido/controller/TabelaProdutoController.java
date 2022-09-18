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

import com.alan.pedido.model.TabelaProduto;
import com.alan.pedido.service.TabelaProdutoService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class TabelaProdutoController {

    @Autowired
    TabelaProdutoService tabelaprodutoService;

    @GetMapping("/tabelaprodutos")
    public List<TabelaProduto> getAllTabelaProdutos() {
        return tabelaprodutoService.lista();
    }

    @PostMapping("/tabelaprodutos")
    public TabelaProduto createTabelaProduto(@Valid @RequestBody TabelaProduto tabelaproduto) {
        return tabelaprodutoService.inserir(tabelaproduto);
    }

    @GetMapping("/tabelaprodutos/{id}")
    public TabelaProduto getTabelaProdutoById(@PathVariable(value = "id") Integer tabelaprodutoId) {
        return tabelaprodutoService.listaId(tabelaprodutoId);
    }

    @PutMapping("/tabelaprodutos/{id}")
    public TabelaProduto updateTabelaProduto(@PathVariable(value = "id") Integer tabelaprodutoId,
                                           @Valid @RequestBody TabelaProduto tabelaprodutoDetails) {

        return tabelaprodutoService.atualiza(tabelaprodutoId, tabelaprodutoDetails);

    }

    @DeleteMapping("/tabelaprodutos/{id}")
    public ResponseEntity<?> deleteTabelaProduto(@PathVariable(value = "id") Integer tabelaprodutoId) {

        tabelaprodutoService.excluir(tabelaprodutoId);

        return ResponseEntity.ok().build();
    }
    
}
