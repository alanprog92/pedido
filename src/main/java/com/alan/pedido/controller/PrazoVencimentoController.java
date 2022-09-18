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

import com.alan.pedido.model.PrazoVencimento;
import com.alan.pedido.service.PrazoVencimentoService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class PrazoVencimentoController {

    @Autowired
    PrazoVencimentoService prazovencimentoService;

    @GetMapping("/prazovencimentos")
    public List<PrazoVencimento> getAllPrazoVencimentos() {
        return prazovencimentoService.lista();
    }

    @PostMapping("/prazovencimentos")
    public PrazoVencimento createPrazoVencimento(@Valid @RequestBody PrazoVencimento prazovencimento) {
        return prazovencimentoService.inserir(prazovencimento);
    }

    @GetMapping("/prazovencimentos/{id}")
    public PrazoVencimento getPrazoVencimentoById(@PathVariable(value = "id") Integer prazovencimentoId) {
        return prazovencimentoService.listaId(prazovencimentoId);
    }

    @PutMapping("/prazovencimentos/{id}")
    public PrazoVencimento updatePrazoVencimento(@PathVariable(value = "id") Integer prazovencimentoId,
                                           @Valid @RequestBody PrazoVencimento prazovencimentoDetails) {

    	return prazovencimentoService.atualiza(prazovencimentoId, prazovencimentoDetails);
    
    }

    @DeleteMapping("/prazovencimentos/{id}")
    public ResponseEntity<?> deletePrazoVencimento(@PathVariable(value = "id") Integer prazovencimentoId) {

        prazovencimentoService.excluir(prazovencimentoId);

        return ResponseEntity.ok().build();
    }
    
}
