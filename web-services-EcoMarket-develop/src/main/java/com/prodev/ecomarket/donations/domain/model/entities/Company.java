package com.prodev.ecomarket.donations.domain.model.entities;

import com.prodev.ecomarket.donations.domain.model.valueobjects.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Company extends Profile {

    @Column(nullable = false, length = 11)
    @Getter
    private String ruc;

    @Column(nullable = false, length = 200)
    @Getter
    private String aboutDescription;


    protected Company() {
    }



}