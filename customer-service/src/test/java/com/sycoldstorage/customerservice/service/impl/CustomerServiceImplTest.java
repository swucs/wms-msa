package com.sycoldstorage.customerservice.service.impl;

import com.sycoldstorage.customerservice.dto.CustomerSearchRequest;
import com.sycoldstorage.customerservice.dto.CustomerSearchResponse;
import com.sycoldstorage.customerservice.dto.Paging;
import com.sycoldstorage.customerservice.entity.Customer;
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

    @Test
    void save() {

        Customer customer = Customer.builder()
                .name("고객명")
                .businessNumber("111-33-44444")
                .representativeName("대표자명")
                .businessConditions("업태")
                .typeOfBusiness("업종")
                .address("경기도 성남시 분당구 운중동")
                .phoneNumber("111-222-3333")
                .faxNumber("444-555-6666")
                .use(true)
                .build();

        customerService.save(customer);
    }
}