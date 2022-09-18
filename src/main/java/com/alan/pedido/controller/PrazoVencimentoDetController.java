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

import com.alan.pedido.model.PrazoVencimentoDet;
import com.alan.pedido.service.PrazoVencimentoDetService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class PrazoVencimentoDetController {

    @Autowired
    PrazoVencimentoDetService prazovencimentodetService;

    @GetMapping("/prazovencimentodets")
    public List<PrazoVencimentoDet> getAllPrazoVencimentoDets() {
        return prazovencimentodetService.lista();
    }

    @PostMapping("/prazovencimentodets")
    public PrazoVencimentoDet createPrazoVencimentoDet(@Valid @RequestBody PrazoVencimentoDet prazovencimentodet) {
    	
        return prazovencimentodetService.inserir(prazovencimentodet);
      
    } 
    
    @GetMapping("/prazovencimentodets/{id}")
    public PrazoVencimentoDet getPrazoVencimentoDetById(@PathVariable(value = "id") Integer prazovencimentodetId) {
        return prazovencimentodetService.listaId(prazovencimentodetId);
    }

    @PutMapping("/prazovencimentodets/{id}")
    public PrazoVencimentoDet updatePrazoVencimentoDet(@PathVariable(value = "id") Integer prazovencimentodetId,
                                           @Valid @RequestBody PrazoVencimentoDet prazovencimentodetDetails) {

        return prazovencimentodetService.atualiza(prazovencimentodetId, prazovencimentodetDetails);

    }

    @DeleteMapping("/prazovencimentodets/{id}")
    public ResponseEntity<?> deletePrazoVencimentoDet(@PathVariable(value = "id") Integer prazovencimentodetId) {

        prazovencimentodetService.excluir(prazovencimentodetId);

        return ResponseEntity.ok().build();
    }
    
}
