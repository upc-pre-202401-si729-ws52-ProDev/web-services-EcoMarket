package com.prodev.ecomarket.donations.interfaces.rest.transform;

import com.prodev.ecomarket.donations.domain.model.commands.CreateDonationCommand;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.prodev.ecomarket.donations.interfaces.rest.resources.CreateDonationResource;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.CompanyRepository;

public class CreateDonationCommandFromResourceAssembler {

    public static CreateDonationCommand toCommandFromResource(CreateDonationResource resource, ProductRepository productRepository, CompanyRepository companyRepository) {
        var Product = productRepository.findById(resource.productId()).get();
        var Company = companyRepository.findById(resource.companyId()).get();

        if(Product == null || Company == null)
            throw new IllegalArgumentException("Product or Company not found");

        return new CreateDonationCommand(
                resource.quantity(),
                resource.description(),
                resource.ong(),
                Product,
                Company);
    }
}