package com.prodev.ecomarket.purchases.interfaces.rest;

import com.prodev.ecomarket.purchases.domain.model.queries.GetCustomerByIdQuery;
import com.prodev.ecomarket.purchases.domain.services.CustomerCommandService;
import com.prodev.ecomarket.purchases.domain.services.CustomerQueryService;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.CreateCustomerResource;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.CustomerResource;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.PurchaseResource;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.UpdateCustomerResource;
import com.prodev.ecomarket.purchases.interfaces.rest.transform.CreateCustomerCommandFromResourceAssembles;
import com.prodev.ecomarket.purchases.interfaces.rest.transform.CustomerResourceFromEntityAssembler;
import com.prodev.ecomarket.purchases.interfaces.rest.transform.UpdateCustomerCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/customers", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Customers", description = "Customers Management Endpoints")
public class CustomerController {
    private final CustomerCommandService customerCommandService;
    private final CustomerQueryService customerQueryService;

    public CustomerController(CustomerCommandService customerCommandService, CustomerQueryService customerQueryService) {
        this.customerCommandService = customerCommandService;
        this.customerQueryService = customerQueryService;
    }

    @PostMapping
    public ResponseEntity<CustomerResource> createCustomer(@RequestBody CreateCustomerResource resource){
        try{
            var createCustomerCommand = CreateCustomerCommandFromResourceAssembles.toCommandFromResource(resource);
            var customer = customerCommandService.handle(createCustomerCommand);
            var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(customer.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(customerResource);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CustomerResource>> getCustomerById(@PathVariable Long customerId){
        try{
            var customer = customerQueryService.handle(new GetCustomerByIdQuery(customerId));
            var customerResources = customer.stream()
                    .map(CustomerResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(customerResources);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResource> updateCustomer(@PathVariable Long customerId, @RequestBody UpdateCustomerResource resource) {
        try {
            var updateCustomerCommand = UpdateCustomerCommandFromResourceAssembler.toCommandFromResource(customerId, resource);
            var updatedCustomer = customerCommandService.handle(updateCustomerCommand);
            var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(updatedCustomer.get());
            return ResponseEntity.ok(customerResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
