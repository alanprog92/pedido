package com.alan.pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alan.pedido.model.Cliente;

/**
 * Created by Alan.
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query("SELECT p from Cliente p WHERE nome LIKE :nome ORDER BY nome")
	public List<Cliente> BuscaClientes(@Param("nome") String nome);	
}
