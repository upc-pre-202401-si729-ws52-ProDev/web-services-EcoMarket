package com.prodev.ecomarket.ordering.interfaces.rest.transform;

import com.prodev.ecomarket.ordering.domain.model.commands.CreateShoppingCartCommand;
import com.prodev.ecomarket.ordering.interfaces.rest.resources.CreateShoppingCartResource;

public class CreateShoppingCartCommandFromResourceAssembler {

    public static CreateShoppingCartCommand fromResource(CreateShoppingCartResource resource) {
        return new CreateShoppingCartCommand();
    }
}
