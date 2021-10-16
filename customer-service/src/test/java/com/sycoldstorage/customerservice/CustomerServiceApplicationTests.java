package com.sycoldstorage.customerservice;

import com.sycoldstorage.customerservice.dto.CustomerSearchRequest;
import com.sycoldstorage.customerservice.entity.Customer;
import com.sycoldstorage.customerservice.service.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class CustomerServiceApplicationTests {

    @Autowired
    CustomerService customerService;

    @Test
    void searchCustomers() {

        CustomerSearchRequest customerSearchRequest = CustomerSearchRequest.builder()
                .page(1)
                .page(10)
                .build();

        Page<Customer> customers = customerService.searchCustomers(customerSearchRequest);

        Assertions.assertThat(customers.getTotalElements()).isEqualTo(35);
        Assertions.assertThat(customers.getTotalPages()).isEqualTo(4);
        Assertions.assertThat(customers.getContent().size()).isEqualTo(10);

    }

}
