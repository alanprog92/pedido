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

import com.alan.pedido.model.FormaPgto;
import com.alan.pedido.service.FormaPgtoService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class FormaPgtoController {

    @Autowired
    FormaPgtoService formapgtoService;

    @GetMapping("/formapgtos")
    public List<FormaPgto> getAllFormaPgtos() {
        return formapgtoService.lista();
    }

    @PostMapping("/formapgtos")
    public FormaPgto createFormaPgto(@Valid @RequestBody FormaPgto formapgto) {
        return formapgtoService.inserir(formapgto);
    }

    @GetMapping("/formapgtos/{id}")
    public FormaPgto getFormaPgtoById(@PathVariable(value = "id") Integer formapgtoId) {
        return formapgtoService.listaId(formapgtoId);
    }

    @PutMapping("/formapgtos/{id}")
    public FormaPgto updateFormaPgto(@PathVariable(value = "id") Integer formapgtoId,
                                           @Valid @RequestBody FormaPgto formapgtoDetails) {

        return formapgtoService.atualiza(formapgtoId, formapgtoDetails);
    }

    @DeleteMapping("/formapgtos/{id}")
    public ResponseEntity<?> deleteFormaPgto(@PathVariable(value = "id") Integer formapgtoId) {

        formapgtoService.excluir(formapgtoId);

        return ResponseEntity.ok().build();
    }
    
}
