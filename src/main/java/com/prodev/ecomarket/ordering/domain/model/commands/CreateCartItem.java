package com.prodev.ecomarket.ordering.domain.model.commands;

import com.prodev.ecomarket.donations.domain.model.entities.Product;

public record CreateCartItem(int quantity, Product product) {
}
