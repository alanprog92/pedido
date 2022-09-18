package com.alan.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alan.pedido.model.FormaPgto;

/**
 * Created by Alan.
 */

@Repository
public interface FormaPgtoRepository extends JpaRepository<FormaPgto, Integer> {

}
