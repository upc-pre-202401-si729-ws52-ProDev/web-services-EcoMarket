package com.prodev.ecomarket.purchases.interfaces.rest;

import com.prodev.ecomarket.purchases.domain.model.queries.GetPurchasesByCustomerIdQuery;
import com.prodev.ecomarket.purchases.domain.services.PurchaseCommandService;
import com.prodev.ecomarket.purchases.domain.services.PurchaseQueryService;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PaymentRepository;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PurchaseRepository;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.CreatePurchaseResource;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.PurchaseResource;
import com.prodev.ecomarket.purchases.interfaces.rest.transform.CreatePurchaseCommandFromResourceAssembler;
import com.prodev.ecomarket.purchases.interfaces.rest.transform.PurchaseResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/purchases", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Purchases", description = "Purchases Management Endpoints")
public class PurchasesController {
    private final PurchaseCommandService purchaseCommandService;
    private final PurchaseQueryService purchaseQueryService;
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;

    public PurchasesController(PurchaseCommandService purchaseService, PurchaseQueryService purchaseQueryService, CustomerRepository customerRepository, PaymentRepository paymentRepository) {
        this.purchaseCommandService = purchaseService;
        this.purchaseQueryService = purchaseQueryService;
        this.customerRepository = customerRepository;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping
    public ResponseEntity<PurchaseResource> createPurchase(@RequestBody CreatePurchaseResource createPurchaseResource){
        var createPurchaseCommand = CreatePurchaseCommandFromResourceAssembler.toCommandFromResource(createPurchaseResource, customerRepository, paymentRepository);

        var purchase = purchaseCommandService.handle(createPurchaseCommand);

        var purchaseResource = PurchaseResourceFromEntityAssembler.toPurchaseFromEntity(purchase.get());
        return new ResponseEntity<>(purchaseResource, HttpStatus.CREATED);
    }

    //TODO: Implementar obtencion de compras por ID de cliente
    @GetMapping("/{customerId}")
    public ResponseEntity<List<PurchaseResource>> getPurchasesByCustomerId(@PathVariable Long customerId){
        try {
            var purchases = purchaseQueryService.handle(new GetPurchasesByCustomerIdQuery(customerId));
            var purchaseResources = purchases.stream()
                    .map(PurchaseResourceFromEntityAssembler::toPurchaseFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(purchaseResources);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
