package com.prodev.ecomarket.iam.interfaces.rest.transform;

import com.prodev.ecomarket.donations.domain.model.commands.CreateCompanyCommand;
import com.prodev.ecomarket.iam.interfaces.rest.resources.SignUpResource;

import java.util.Optional;

public class CreateCompanyCommandFromSignUpCommand {

    public static CreateCompanyCommand toCreateCompanyCommand(SignUpResource signUpResource) {
        Optional<String> ruc = signUpResource.ruc();
        Optional<String> aboutDescription = signUpResource.aboutDescription();
        var name = signUpResource.username();

        if (ruc.isEmpty() || aboutDescription.isEmpty() || name.isEmpty()) {
            throw new IllegalArgumentException("RUC, description and name are required");
        }

        return new CreateCompanyCommand(name,ruc.get(), aboutDescription.get());
    }
}