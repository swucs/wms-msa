package com.sycoldstorage.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sycoldstorage.customerservice.dto.CustomerSearchRequest;
import com.sycoldstorage.customerservice.entity.Customer;
import com.sycoldstorage.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

//    @GetMapping("/customers")
//    public ResponseEntity getCustomers(@RequestBody CustomerSearchRequest params) {
//
//        Page<Customer> customers = customerService.searchCustomers(params);
//        ResponseEntity.ok(customers)
//
//    }

}
