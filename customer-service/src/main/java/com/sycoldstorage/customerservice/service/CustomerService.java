package com.sycoldstorage.customerservice.service;

import com.sycoldstorage.customerservice.dto.CustomerSearchRequest;
import com.sycoldstorage.customerservice.dto.CustomerSearchResponse;
import com.sycoldstorage.customerservice.dto.Paging;
import com.sycoldstorage.customerservice.entity.Customer;

import javax.transaction.Transactional;

public interface CustomerService {
    Paging<CustomerSearchResponse> searchCustomers(CustomerSearchRequest params);

    @Transactional
    void save(Customer customer);
}
