package com.prodev.ecomarket.ordering.domain.model.commands;

import com.prodev.ecomarket.purchases.domain.model.entities.Customer;

public record CreateShoppingCartCommand(Customer customer){

}
