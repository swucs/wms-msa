package com.sycoldstorage.customerservice.service;

import com.sycoldstorage.customerservice.dto.SaveCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerResponse;
import com.sycoldstorage.customerservice.entity.Customer;
import com.sycoldstorage.customerservice.exception.NoSuchDataException;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

public interface CustomerService {
    Page<Customer> searchCustomers(SearchCustomerRequest params);

    @Transactional
    Customer create(SaveCustomerRequest saveCustomerRequest);

    @Transactional
    Customer update(SaveCustomerRequest saveCustomerRequest) throws NoSuchDataException;

    @Transactional
    Customer delete(Long id) throws NoSuchDataException;
}
