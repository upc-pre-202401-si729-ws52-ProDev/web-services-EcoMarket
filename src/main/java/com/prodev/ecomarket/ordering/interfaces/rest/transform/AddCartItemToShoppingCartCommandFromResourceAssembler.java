package com.prodev.ecomarket.ordering.interfaces.rest.transform;

import com.prodev.ecomarket.ordering.domain.model.commands.AddCartItemToShoppingCartCommand;
import com.prodev.ecomarket.ordering.domain.model.commands.CreateCartItem;
import com.prodev.ecomarket.ordering.interfaces.rest.resources.CreateCartItemResource;

public class AddCartItemToShoppingCartCommandFromResourceAssembler {

    public static AddCartItemToShoppingCartCommand fromResource(CreateCartItemResource resource) {
        return new AddCartItemToShoppingCartCommand(resource.quantity(), resource.productId(), resource.shoppingCartId());
    }
}
