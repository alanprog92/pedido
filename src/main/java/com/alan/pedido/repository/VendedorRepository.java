package com.alan.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alan.pedido.model.Vendedor;

/**
 * Created by Alan.
 */

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

}
