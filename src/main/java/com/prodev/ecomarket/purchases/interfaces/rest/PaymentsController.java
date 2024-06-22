package com.prodev.ecomarket.purchases.interfaces.rest;

import com.prodev.ecomarket.purchases.domain.model.entities.Customer;
import com.prodev.ecomarket.purchases.domain.model.queries.GetPaymentsByCustomerIdQuery;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;

@RestController
@RequestMapping(value="/api/v1/payments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Payments Management Endpoints")

public class PaymentsController {
    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;
    private CustomerRepository customerRepository; // Add this line


    public PaymentsController(PaymentCommandService paymentCommandService, PaymentQueryService paymentQueryService, CustomerRepository customerRepository){
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
        this.customerRepository = customerRepository; // Add this line
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

    //TODO: Implementar obtencion de pagos por ID de cliente

    @GetMapping("/{customerId}")
    public ResponseEntity<List<PaymentResource>> getPaymentsByCustomerId(@PathVariable Long customerId) {
        try {
            var payments = paymentQueryService.handle(new GetPaymentsByCustomerIdQuery(customerId));
            var paymentResources = payments.stream()
                    .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(paymentResources);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /*var getPaymentsByCustomerIdQuery = new GetPaymentsByCustomerIdQuery(customerId);
        var payments = paymentQueryService.handle(getPaymentsByCustomerIdQuery);
        if (payments.isEmpty()) return ResponseEntity.badRequest().build();
        var paymentResources = PaymentResourceFromEntityAssembler.toResourceFromEntity(payments.get());*/
    }

}
