package com.prodev.ecomarket.purchases.interfaces.rest.transform;

import com.prodev.ecomarket.purchases.domain.model.entities.Customer;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.CustomerResource;

public class CustomerResourceFromEntityAssembler {
    public static CustomerResource toResourceFromEntity(Customer entity){
        return new CustomerResource(
                entity.getId(),
                entity.getLoyaltyPoi(),
                entity.getName(),
                entity.getLastName(),
                entity.getAge(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getPhone()
        );
    }
}
