package com.prodev.ecomarket.donations.infrastructure.persistence.jps.repositories;

import com.prodev.ecomarket.donations.domain.model.aggregates.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

}
