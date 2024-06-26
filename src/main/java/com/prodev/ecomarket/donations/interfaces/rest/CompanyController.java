package com.prodev.ecomarket.donations.interfaces.rest;

import com.prodev.ecomarket.donations.domain.model.entities.Company;
import com.prodev.ecomarket.donations.domain.model.queries.GetAllCompanysQuery;
import com.prodev.ecomarket.donations.domain.model.queries.GetCompanyByIdQuery;
import com.prodev.ecomarket.donations.domain.services.CompanyCommandService;
import com.prodev.ecomarket.donations.domain.services.CompanyQueryService;
import com.prodev.ecomarket.donations.interfaces.rest.resources.CompanyResource;
import com.prodev.ecomarket.donations.interfaces.rest.resources.CreateCompanyResource;
import com.prodev.ecomarket.donations.interfaces.rest.transform.CompanyResourceFromEntityAssembler;
import com.prodev.ecomarket.donations.interfaces.rest.transform.CreateCompanyCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value ="/api/v1/companies", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Companies", description = "Companies Management Endpoints")
public class CompanyController {
    private final CompanyCommandService companyCommandService;
    private final CompanyQueryService companyQueryService;

    public CompanyController(CompanyCommandService companyCommandService, CompanyQueryService companyQueryService) {
        this.companyCommandService = companyCommandService;
        this.companyQueryService = companyQueryService;
    }

    @PostMapping
    public ResponseEntity<CompanyResource> createCompany(@RequestBody CreateCompanyResource createCompanyResource) {
        var createCompanyCommand = CreateCompanyCommandFromResourceAssembler.toCommandFromResource(createCompanyResource);

        var company = companyCommandService.handle(createCompanyCommand);

        var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(company.get());
        return new ResponseEntity<>(companyResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResource>> getAllCompanies() {
        GetAllCompanysQuery query = new GetAllCompanysQuery();
        List<Company> companies = companyQueryService.handle(query);

        List<CompanyResource> companyResources = companies.stream()
                .map(CompanyResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(companyResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResource> getCompany(@PathVariable Long id) {
        GetCompanyByIdQuery query = new GetCompanyByIdQuery(id);
        Optional<Company> company = companyQueryService.handle(query);

        if (company.isPresent()) {
            CompanyResource companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(company.get());
            return ResponseEntity.ok(companyResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
