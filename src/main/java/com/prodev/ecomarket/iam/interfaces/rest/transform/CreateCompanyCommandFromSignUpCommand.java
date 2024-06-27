package com.prodev.ecomarket.iam.interfaces.rest.transform;

import com.prodev.ecomarket.donations.domain.model.commands.CreateCompanyCommand;
import com.prodev.ecomarket.iam.interfaces.rest.resources.SignUpResource;

import java.util.Optional;

public class CreateCompanyCommandFromSignUpCommand {

    public static CreateCompanyCommand toCreateCompanyCommand(SignUpResource signUpResource) {
        Optional<String> ruc = signUpResource.ruc();
        Optional<String> aboutDescription = signUpResource.aboutDescription();

        if (ruc.isEmpty() || aboutDescription.isEmpty()) {
            throw new IllegalArgumentException("RUC and aboutDescription are required");
        }

        return new CreateCompanyCommand(ruc.get(), aboutDescription.get());
    }
}