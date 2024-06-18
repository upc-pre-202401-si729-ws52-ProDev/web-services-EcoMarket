package com.prodev.ecomarket.purchases.interfaces.rest;

import com.prodev.ecomarket.purchases.domain.services.PaymentCommandService;
import com.prodev.ecomarket.purchases.domain.services.PaymentQueryService;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.CreatePaymentResource;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.PaymentResource;
import com.prodev.ecomarket.purchases.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.prodev.ecomarket.purchases.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api/v1//payments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Payments Management Endpoints")

public class PaymentsController {
    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public PaymentsController(PaymentCommandService paymentCommandService, PaymentQueryService paymentQueryService){
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    @PostMapping
    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource resource){
        try{
            var createPaymentCommand = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource);
            var payment = paymentCommandService.handle(createPaymentCommand);
            var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(payment.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
