package com.prodev.ecomarket.iam.interfaces.rest;


import com.prodev.ecomarket.donations.domain.model.commands.CreateCompanyCommand;
import com.prodev.ecomarket.donations.domain.services.CompanyCommandService;
import com.prodev.ecomarket.iam.domain.services.UserCommandService;
import com.prodev.ecomarket.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.prodev.ecomarket.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.prodev.ecomarket.iam.interfaces.rest.resources.SignInResource;
import com.prodev.ecomarket.iam.interfaces.rest.resources.SignUpResource;
import com.prodev.ecomarket.iam.interfaces.rest.resources.UserResource;
import com.prodev.ecomarket.iam.interfaces.rest.transform.*;
import com.prodev.ecomarket.purchases.domain.model.commands.CreateCustommerCommand;
import com.prodev.ecomarket.purchases.domain.services.CustomerCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.prodev.ecomarket.iam.interfaces.rest.transform.CreateCompanyCommandFromSignUpCommand.toCreateCompanyCommand;
import static com.prodev.ecomarket.iam.interfaces.rest.transform.CreateCustomerCommandFromSignUpResource.toCreateCustomerCommand;

/**
 * AuthenticationController
 * <p>
 *     This controller is responsible for handling authentication requests.
 *     It exposes two endpoints:
 *     <ul>
 *         <li>POST /api/v1/auth/sign-in</li>
 *         <li>POST /api/v1/auth/sign-up</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;
    private final CompanyCommandService companyCommandService;
    private final UserRepository userRepository;
    private final CustomerCommandService customerCommandService;

    public AuthenticationController(UserCommandService userCommandService, CompanyCommandService companyCommandService, UserRepository userRepository, CustomerCommandService customerCommandService) {
        this.userCommandService = userCommandService;
        this.companyCommandService = companyCommandService;
        this.userRepository = userRepository;
        this.customerCommandService = customerCommandService;
    }

    /**
     * Handles the sign-in request.
     * @param signInResource the sign-in request body.
     * @return the authenticated user resource.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    /**
     * Handles the sign-up request.
     * @param signUpResource the sign-up request body.
     * @return the created user resource.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Crear y asociar Company con el User
        if (signUpResource.roles().contains("ROLE_COMPANY")) {
            CreateCompanyCommand createCompanyCommand = toCreateCompanyCommand(signUpResource);
            var company = companyCommandService.handle(createCompanyCommand);
            if (company.isPresent()) {
                user.get().setCompany(company.get());
                userRepository.save(user.get());
            }
        } else {
            CreateCustommerCommand createCustommerCommand = toCreateCustomerCommand(signUpResource);
            var customer = customerCommandService.handle(createCustommerCommand);
            if (customer.isPresent()) {
                user.get().setCustomer(customer.get());
                userRepository.save(user.get());
            }
        }

        // Recuperar el usuario con los roles
        var userWithRoles = userRepository.findById(user.get().getId());
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(userWithRoles.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}
