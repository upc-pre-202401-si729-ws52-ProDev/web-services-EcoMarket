package com.prodev.ecomarket.iam.interfaces.rest.transform;


import com.prodev.ecomarket.iam.domain.model.entities.Role;
import com.prodev.ecomarket.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
