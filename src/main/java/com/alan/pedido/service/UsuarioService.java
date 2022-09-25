package com.alan.pedido.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alan.pedido.dto.UsuarioDTO;
import com.alan.pedido.exception.ResourceNotFoundException;
import com.alan.pedido.model.Usuario;
import com.alan.pedido.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
    @Autowired
    UsuarioRepository usuarioRepository;  
	
	public List<UsuarioDTO> lista(){
		
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		
		List<Usuario> usuarios =  usuarioRepository.findAll();
		
		for (Usuario usuario : usuarios) {
			
			UsuarioDTO u = new UsuarioDTO();
			u.setId(usuario.getId());
			u.setLogin(usuario.getLogin());
			u.setVendedor(usuario.getVendedor());
			usuariosDTO.add(u);
		}
		
		return usuariosDTO;
	}
	
	public UsuarioDTO listaId(Integer usuarioId){

		Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setLogin(usuario.getLogin());
		usuarioDTO.setVendedor(usuario.getVendedor());	
		
		return usuarioDTO;
	}
	
    public Usuario inserir(Usuario usuario) {    
    	
		String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhacriptografada);    	
        
        return usuarioRepository.save(usuario);
        
    }
	
    public List<UsuarioDTO> buscaUsuario( Usuario usuario) {
    	
    	List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
    	
		List<Usuario> usuarios = usuarioRepository.buscaUsuario(usuario.getLogin(), usuario.getSenha());
		
		for (Usuario item : usuarios) {
			
			UsuarioDTO u = new UsuarioDTO();
			u.setId(item.getId());
			u.setLogin(item.getLogin());
			u.setVendedor(item.getVendedor());
			usuariosDTO.add(u);
		}  	
    	
    	
        return usuariosDTO;
        
    }  
    
	public Usuario atualiza(Integer usuarioId,Usuario usuarioDetails){
 

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));  
 
        usuario.setId(usuarioDetails.getId());
        usuario.setVendedor(usuarioDetails.getVendedor());
        usuario.setLogin(usuarioDetails.getLogin());

		Usuario userTemporario = usuarioRepository.findUserByLogin(usuario.getLogin());
        
		if (!userTemporario.getSenha().equals(usuario.getSenha())) { /*Senhas diferentes*/
			String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
			usuario.setSenha(senhacriptografada);
		}      

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
