package com.prodev.ecomarket.Review.Interface.rest.Resources;

import java.time.LocalDate;

public record ReviewResources(Long id, String content, int calification, LocalDate date, String product) {
}
