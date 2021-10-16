package com.sycoldstorage.customerservice.service;

import com.sycoldstorage.customerservice.dto.CustomerSearchRequest;
import com.sycoldstorage.customerservice.entity.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    Page<Customer> searchCustomers(CustomerSearchRequest params);
}
