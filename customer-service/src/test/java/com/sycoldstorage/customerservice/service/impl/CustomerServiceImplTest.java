package com.sycoldstorage.customerservice.service.impl;

import com.sycoldstorage.customerservice.dto.CustomerSearchRequest;
import com.sycoldstorage.customerservice.dto.CustomerSearchResponse;
import com.sycoldstorage.customerservice.dto.Paging;
import com.sycoldstorage.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    CustomerService customerService;

    @Test
    void searchCustomers() {

        CustomerSearchRequest customerSearchRequest = CustomerSearchRequest.builder()
                .page(1)
                .pageSize(10)
                .build();

        Paging<CustomerSearchResponse> customers = customerService.searchCustomers(customerSearchRequest);

        assertThat(customers.getTotalCount()).isEqualTo(35);
        assertThat(customers.getTotalPages()).isEqualTo(4);
        assertThat(customers.getContent().size()).isEqualTo(10);

    }
}