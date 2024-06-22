package com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories;

import com.prodev.ecomarket.donations.domain.model.aggregates.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByCompanyId(Long companyId);
    List<Donation> findByOng(String ong);
}
