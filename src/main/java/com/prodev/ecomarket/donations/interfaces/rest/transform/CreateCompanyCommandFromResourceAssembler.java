package com.prodev.ecomarket.donations.interfaces.rest.transform;

import com.prodev.ecomarket.donations.domain.model.commands.CreateCompanyCommand;
import com.prodev.ecomarket.donations.interfaces.rest.resources.CreateCompanyResource;

public class CreateCompanyCommandFromResourceAssembler {
    public static CreateCompanyCommand toCommandFromResource(CreateCompanyResource resource) {
        return new CreateCompanyCommand(
                resource.name(),
                resource.ruc(),
                resource.aboutDescription()
        );
    }
}
