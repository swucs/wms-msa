package com.sycoldstorage.customerservice.service;

import com.sycoldstorage.customerservice.dto.CustomerSearchRequest;
import com.sycoldstorage.customerservice.dto.CustomerSearchResponse;
import com.sycoldstorage.customerservice.dto.Paging;

public interface CustomerService {
    Paging<CustomerSearchResponse> searchCustomers(CustomerSearchRequest params);
}
