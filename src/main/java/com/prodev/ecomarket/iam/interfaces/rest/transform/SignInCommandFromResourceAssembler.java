package com.prodev.ecomarket.iam.interfaces.rest.transform;


import com.prodev.ecomarket.iam.domain.model.commands.SignInCommand;
import com.prodev.ecomarket.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
