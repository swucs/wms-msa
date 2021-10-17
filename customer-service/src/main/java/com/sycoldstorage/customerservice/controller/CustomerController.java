package com.sycoldstorage.customerservice.controller;

import com.sycoldstorage.customerservice.dto.CustomerSearchRequest;
import com.sycoldstorage.customerservice.dto.CustomerSearchResponse;
import com.sycoldstorage.customerservice.dto.Paging;
import com.sycoldstorage.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity getCustomers(@ModelAttribute CustomerSearchRequest params) {
        Paging<CustomerSearchResponse> customers = customerService.searchCustomers(params);
        return ResponseEntity.ok().body(customers);
    }

}
