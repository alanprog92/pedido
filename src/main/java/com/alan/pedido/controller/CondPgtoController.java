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

import com.alan.pedido.model.CondPgto;
import com.alan.pedido.service.CondPgtoService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class CondPgtoController {

    @Autowired
    CondPgtoService condpgtoService;

    @GetMapping("/condpgtos")
    public List<CondPgto> getAllCondPgtos() {
        return condpgtoService.lista();
    }

    @PostMapping("/condpgtos")
    public CondPgto createCondPgto(@Valid @RequestBody CondPgto condpgto) {
        return condpgtoService.inserir(condpgto);
    }

    @GetMapping("/condpgtos/{id}")
    public CondPgto getCondPgtoById(@PathVariable(value = "id") Integer condpgtoId) {
        return condpgtoService.listaId(condpgtoId);
    }

    @PutMapping("/condpgtos/{id}")
    public CondPgto updateCondPgto(@PathVariable(value = "id") Integer condpgtoId,
                                           @Valid @RequestBody CondPgto condpgtoDetails) {
    	
    	return condpgtoService.atualiza( condpgtoId, condpgtoDetails);
    }

    @DeleteMapping("/condpgtos/{id}")
    public ResponseEntity<?> deleteCondPgto(@PathVariable(value = "id") Integer condpgtoId) {
    	
        condpgtoService.excluir(condpgtoId);

        return ResponseEntity.ok().build();
    }
    
}
