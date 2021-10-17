package com.sycoldstorage.customerservice.service;

import com.sycoldstorage.customerservice.dto.SaveCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerResponse;
import com.sycoldstorage.customerservice.dto.Paging;
import com.sycoldstorage.customerservice.entity.Customer;

import javax.transaction.Transactional;

public interface CustomerService {
    Paging<SearchCustomerResponse> searchCustomers(SearchCustomerRequest params);

    @Transactional
    long save(SaveCustomerRequest saveCustomerRequest);
}
