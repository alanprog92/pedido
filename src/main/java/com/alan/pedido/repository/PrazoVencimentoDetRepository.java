package com.alan.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alan.pedido.model.PrazoVencimentoDet;

/**
 * Created by Alan.
 */

@Repository
public interface PrazoVencimentoDetRepository extends JpaRepository<PrazoVencimentoDet, Integer> {

}
