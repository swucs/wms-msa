package com.sycoldstorage.customerservice.service;

import com.sycoldstorage.customerservice.dto.SaveCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerResponse;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

public interface CustomerService {
    Page<SearchCustomerResponse> searchCustomers(SearchCustomerRequest params);

    @Transactional
    long save(SaveCustomerRequest saveCustomerRequest);
}
