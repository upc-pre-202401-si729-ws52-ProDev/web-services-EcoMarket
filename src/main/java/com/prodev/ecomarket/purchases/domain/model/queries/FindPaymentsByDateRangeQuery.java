package com.prodev.ecomarket.purchases.domain.model.queries;

import java.util.Date;

public record FindPaymentsByDateRangeQuery(Date startDate, Date endDate) {
}
