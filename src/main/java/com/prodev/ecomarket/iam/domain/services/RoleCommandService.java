package com.prodev.ecomarket.iam.domain.services;


import com.prodev.ecomarket.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
