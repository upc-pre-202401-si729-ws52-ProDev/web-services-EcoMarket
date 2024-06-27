package com.prodev.ecomarket.iam.interfaces.rest.transform;


import com.prodev.ecomarket.iam.domain.model.aggregates.User;
import com.prodev.ecomarket.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}
