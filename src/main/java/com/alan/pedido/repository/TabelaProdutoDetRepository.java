package com.alan.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alan.pedido.model.TabelaProdutoDet;

/**
 * Created by Alan.
 */

@Repository
public interface TabelaProdutoDetRepository extends JpaRepository<TabelaProdutoDet, Integer> {

}
