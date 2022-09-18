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

import com.alan.pedido.model.Usuario;
import com.alan.pedido.service.UsuarioService;

/**
 * Created by Alan.
 */
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.lista();
    }

    @PostMapping("/usuarios")
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.inserir(usuario);
    }
    
    @PostMapping("/usuarios/buscausuario")
    public List<Usuario> buscaUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.buscaUsuario(usuario);
        
    }    

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable(value = "id") Integer usuarioId) {
        return usuarioService.listaId(usuarioId);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario updateUsuario(@PathVariable(value = "id") Integer usuarioId,
                                           @Valid @RequestBody Usuario usuarioDetails) {

        return usuarioService.atualiza(usuarioId, usuarioDetails);

    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Integer usuarioId) {

        usuarioService.excluir(usuarioId);
        return ResponseEntity.ok().build();
        
    }
    
}
