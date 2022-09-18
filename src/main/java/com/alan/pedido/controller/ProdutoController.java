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

import com.alan.pedido.model.Produto;
import com.alan.pedido.service.ProdutoService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/produtos")
    public List<Produto> getAllProdutos() {
        return produtoService.lista();
    }

    @PostMapping("/produtos")
    public Produto createProduto(@Valid @RequestBody Produto produto) {
        return produtoService.inserir(produto);
    }

    @GetMapping("/produtos/{id}")
    public Produto getProdutoById(@PathVariable(value = "id") Integer produtoId) {
        return produtoService.listaId(produtoId);
    }

    @PutMapping("/produtos/{id}")
    public Produto updateProduto(@PathVariable(value = "id") Integer produtoId,
                                           @Valid @RequestBody Produto produtoDetails) {

        return produtoService.atualiza(produtoId, produtoDetails);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable(value = "id") Integer produtoId) {

        produtoService.excluir(produtoId);

        return ResponseEntity.ok().build();
    }
    
}
