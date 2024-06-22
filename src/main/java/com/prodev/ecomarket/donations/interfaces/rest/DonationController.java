package com.prodev.ecomarket.donations.interfaces.rest;

import com.prodev.ecomarket.donations.domain.model.aggregates.Donation;
import com.prodev.ecomarket.donations.domain.services.DonationCommandService;
import com.prodev.ecomarket.donations.domain.services.DonationQueryService;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.CompanyRepository;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.prodev.ecomarket.donations.interfaces.rest.resources.CreateDonationResource;
import com.prodev.ecomarket.donations.interfaces.rest.resources.DonationResource;
import com.prodev.ecomarket.donations.interfaces.rest.transform.CreateDonationCommandFromResourceAssembler;
import com.prodev.ecomarket.donations.interfaces.rest.transform.DonationResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value ="/api/v1/donations", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Donations", description = "Donations Management Endpoints")
public class DonationController {
    private final DonationCommandService donationCommandService;
    private final DonationQueryService donationQueryService;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    public DonationController(DonationCommandService donationService, DonationQueryService donationQueryService, ProductRepository productRepository, CompanyRepository companyRepository) {
        this.donationCommandService = donationService;
        this.donationQueryService = donationQueryService;
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
    }

    @PostMapping
    public ResponseEntity<DonationResource> createDonation(@RequestBody CreateDonationResource createDonationResource) {
        var createDonationCommand = CreateDonationCommandFromResourceAssembler.toCommandFromResource(createDonationResource, productRepository, companyRepository);

        var donation = donationCommandService.handle(createDonationCommand);

        var donationResource = DonationResourceFromEntityAssembler.toResourceFromEntity(donation.get());
        return new ResponseEntity<>(donationResource, HttpStatus.CREATED);
    }
}
