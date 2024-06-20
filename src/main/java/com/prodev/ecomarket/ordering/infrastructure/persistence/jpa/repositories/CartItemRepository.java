package com.prodev.ecomarket.ordering.infrastructure.persistence.jpa.repositories;

import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
