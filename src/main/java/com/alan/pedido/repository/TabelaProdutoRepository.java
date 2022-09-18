package com.alan.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alan.pedido.model.TabelaProduto;

/**
 * Created by Alan.
 */

@Repository
public interface TabelaProdutoRepository extends JpaRepository<TabelaProduto, Integer> {

}
