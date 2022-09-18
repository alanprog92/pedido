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

import com.alan.pedido.model.Vendedor;
import com.alan.pedido.service.VendedorService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class VendedorController {

    @Autowired
    VendedorService vendedorService;

    @GetMapping("/vendedores")
    public List<Vendedor> getAllVendedors() {
        return vendedorService.lista();
    }

    @PostMapping("/vendedores")
    public Vendedor createVendedor(@Valid @RequestBody Vendedor vendedor) {
        return vendedorService.inserir(vendedor);
    }

    @GetMapping("/vendedores/{id}")
    public Vendedor getVendedorById(@PathVariable(value = "id") Integer vendedorId) {
        return vendedorService.listaId(vendedorId);
    }

    @PutMapping("/vendedores/{id}")
    public Vendedor updateVendedor(@PathVariable(value = "id") Integer vendedorId,
                                           @Valid @RequestBody Vendedor vendedorDetails) {

    	return vendedorService.atualiza(vendedorId, vendedorDetails);

    }

    @DeleteMapping("/vendedores/{id}")
    public ResponseEntity<?> deleteVendedor(@PathVariable(value = "id") Integer vendedorId) {

        vendedorService.excluir(vendedorId);

        return ResponseEntity.ok().build();
    }
    
}
