package com.prodev.ecomarket.purchases.domain.model.entities;

import com.prodev.ecomarket.donations.domain.model.valueobjects.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@Builder
@AllArgsConstructor
@Entity
public class Customer extends Profile {

    @Column(nullable = false, length = 200)
    private String address;

    @Column(nullable = false, length = 9)
    private int loyaltyPoi;

    public Customer() {
    }
}
