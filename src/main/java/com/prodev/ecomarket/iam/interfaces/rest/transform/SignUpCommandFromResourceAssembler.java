package com.prodev.ecomarket.iam.interfaces.rest.transform;

import com.prodev.ecomarket.iam.domain.model.commands.SignUpCommand;
import com.prodev.ecomarket.iam.domain.model.entities.Role;
import com.prodev.ecomarket.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;
import java.util.Optional;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList() : new ArrayList<Role>();
        return new SignUpCommand(
                resource.username(),
                resource.password(),
                roles,
                Optional.empty(), // ruc
                Optional.empty(), // aboutDescription
                Optional.empty(), // address
                Optional.empty(), // loyaltyPoi
                resource.email(),
                resource.name(),
                resource.phone()
        );
    }
}