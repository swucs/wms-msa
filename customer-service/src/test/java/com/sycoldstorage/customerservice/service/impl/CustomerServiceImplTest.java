package com.sycoldstorage.customerservice.service.impl;

import com.sycoldstorage.customerservice.dto.SaveCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerResponse;
import com.sycoldstorage.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CustomerServiceImplTest {
    @Autowired
    CustomerService customerService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    void searchCustomers() {

        SearchCustomerRequest searchCustomerRequest = SearchCustomerRequest.builder()
                .page(1)
                .pageSize(10)
                .build();

        Page<SearchCustomerResponse> customers = customerService.searchCustomers(searchCustomerRequest);

        assertThat(customers.getTotalElements()).isEqualTo(35);
        assertThat(customers.getTotalPages()).isEqualTo(4);
        assertThat(customers.getContent().size()).isEqualTo(10);

    }

    @Test
    void save() {

        SaveCustomerRequest customer = SaveCustomerRequest.builder()
                .name("고객명")
                .businessNumber("111-33-44444")
                .representativeName("대표자명")
                .businessConditions("업태")
                .typeOfBusiness("업종")
                .address("경기도 성남시 분당구 운중동")
                .phoneNumber("111-222-3333")
                .faxNumber("444-555-6666")
                .useYn("Y")
                .build();


        long id = customerService.save(customer);

        customer = SaveCustomerRequest.builder()
                .id(id)
                .name("고객명1")
                .businessNumber("111-33-55555")
                .representativeName("대표자명1")
                .businessConditions("업태1")
                .typeOfBusiness("업종1")
                .address("경기도 성남시 분당구 운중동1")
                .phoneNumber("111-222-4444")
                .faxNumber("444-555-5555")
                .useYn("N")
                .build();

        customerService.save(customer);
    }
}