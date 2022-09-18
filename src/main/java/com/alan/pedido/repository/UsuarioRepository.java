package com.alan.pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alan.pedido.model.Usuario;

/**
 * Created by Alan.
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("SELECT p FROM Usuario p WHERE login = :login AND senha = :senha")
	public List<Usuario> buscaUsuario(@Param("login") String login,
			                                     @Param("senha") String senha);
	
}
