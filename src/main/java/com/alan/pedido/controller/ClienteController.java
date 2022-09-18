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

import com.alan.pedido.model.Cliente;
import com.alan.pedido.service.ClienteService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> getAllClientes() {
        return clienteService.lista();
    }
    
    @GetMapping("/clientes/buscaclientes")
    public List<Cliente> getBuscaClientes( @RequestParam(value = "nome") String nome ) {
    	
        return clienteService.listaNome(nome+"%");
        
    }     

    @PostMapping("/clientes")
    public Cliente createCliente(@Valid @RequestBody Cliente cliente) {
    	
        return clienteService.inserir(cliente);
    }

    @GetMapping("/clientes/{id}")
    public Cliente getClienteById(@PathVariable(value = "id") Integer clienteId) {
        return clienteService.listaId(clienteId);
    }

    @PutMapping("/clientes/{id}")
    public Cliente updateCliente(@PathVariable(value = "id") Integer clienteId,
                                           @Valid @RequestBody Cliente clienteDetails) {

    	return clienteService.atualiza(clienteId, clienteDetails);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable(value = "id") Integer clienteId) {

    	clienteService.excluir(clienteId);

        return ResponseEntity.ok().build();
    }
    
}
