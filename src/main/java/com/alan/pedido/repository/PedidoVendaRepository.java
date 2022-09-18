package com.alan.pedido.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alan.pedido.model.PedidoVenda;

/**
 * Created by Alan.
 */

@Repository
public interface PedidoVendaRepository extends JpaRepository<PedidoVenda, Integer> {
	
	@Query("SELECT p from PedidoVenda p WHERE p.vendedor.id = :vendedorid "
			+ "AND cliente.nome LIKE :nomecliente "
			+ "AND status LIKE :status "
			+ "AND sincronizado LIKE :sincronizado "
			+ "AND emissao BETWEEN :dataini AND :datafim "
			+ "ORDER BY id DESC")
	public List<PedidoVenda> BuscaPedidos(@Param("vendedorid") Integer vendedorid,
	                                      @Param("nomecliente") String nomecliente,
	                                      @Param("status") String status,	
	                                      @Param("sincronizado") String sincronizado,
	                                      @Param("dataini") LocalDate dataini,
	                                      @Param("datafim") LocalDate datafim);	

}
