package com.prodev.ecomarket.ordering.infrastructure.persistence.jpa.repositories;

import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{

}
