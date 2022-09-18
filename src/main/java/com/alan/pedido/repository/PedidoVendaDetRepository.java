package com.alan.pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alan.pedido.model.PedidoVendaDet;
/**
 * Created by Alan.
 */

@Repository
public interface PedidoVendaDetRepository extends JpaRepository<PedidoVendaDet, Integer> {
	
	@Query("SELECT p from PedidoVendaDet p WHERE pedidovendaid = :pedidovendaid")	
	public List<PedidoVendaDet> BuscaItensPedido(@Param("pedidovendaid") Integer pedidovendaid);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM PedidoVendaDet p WHERE pedidovendaid = :pedidovendaid")	
	public void DeletaItensPedido(@Param("pedidovendaid") Integer pedidovendaid);	

}
