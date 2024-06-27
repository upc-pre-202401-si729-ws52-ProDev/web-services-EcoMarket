package com.prodev.ecomarket.donations.interfaces.rest.transform;

import com.prodev.ecomarket.donations.domain.model.entities.Company;
import com.prodev.ecomarket.donations.interfaces.rest.resources.CompanyResource;

public class CompanyResourceFromEntityAssembler {
    public static CompanyResource toResourceFromEntity(Company company) {
        return new CompanyResource(
                company.getId(),
                company.getName(),
                company.getRuc(),
                company.getAboutDescription()
        );
    }
}
