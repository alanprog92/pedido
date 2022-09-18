package com.alan.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.Usuario;
import com.alan.pedido.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
    @Autowired
    UsuarioRepository usuarioRepository;  
	
	public List<Usuario> lista(){
		return usuarioRepository.findAll();
	}
	
	public Usuario listaId(Integer usuarioId){
		return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
	}
	
    public Usuario inserir(Usuario usuario) {    	
        
        return usuarioRepository.save(usuario);
        
    }
	
    public List<Usuario> buscaUsuario( Usuario usuario) {
        return usuarioRepository.buscaUsuario(usuario.getLogin(), usuario.getSenha());
        
    }  
    
	public Usuario atualiza(Integer usuarioId,Usuario usuarioDetails){
 

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
 
        usuario.setId(usuarioDetails.getId());
        usuario.setVendedor(usuarioDetails.getVendedor());
        usuario.setLogin(usuarioDetails.getLogin());
        usuario.setSenha(usuarioDetails.getSenha());

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return updatedUsuario;
        
	}
	
    public ResponseEntity<?> excluir(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));

        usuarioRepository.delete(usuario);

        return ResponseEntity.ok().build();
    }
	
	
}
