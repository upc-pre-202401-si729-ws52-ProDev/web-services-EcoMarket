package com.prodev.ecomarket.donations.interfaces.rest.transform;

import com.prodev.ecomarket.donations.domain.model.aggregates.Donation;
import com.prodev.ecomarket.donations.interfaces.rest.resources.DonationResource;

public class DonationResourceFromEntityAssembler {
    public static DonationResource toResourceFromEntity(Donation entity) {
        return new DonationResource(
            entity.getId(),
            entity.getOng(),
            entity.getProduct().getName(),
            entity.getCompany().getName(),
            entity.getQuantity(),
            entity.getCreatedAt().toString(),
            entity.getDescription()
        );
    }
}
