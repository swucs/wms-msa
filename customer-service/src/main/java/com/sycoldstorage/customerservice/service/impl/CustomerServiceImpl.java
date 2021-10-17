package com.sycoldstorage.customerservice.service.impl;

import com.sycoldstorage.customerservice.dto.CustomerSearchRequest;
import com.sycoldstorage.customerservice.dto.CustomerSearchResponse;
import com.sycoldstorage.customerservice.dto.Paging;
import com.sycoldstorage.customerservice.entity.Customer;
import com.sycoldstorage.customerservice.repository.CustomerRepository;
import com.sycoldstorage.customerservice.service.CustomerService;
import com.sycoldstorage.customerservice.spec.CustomerSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 고객 Service
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 고객목록 검색
     * @param params 검색조건을 담은 객체
     * @return
     */
    @Override
    public Paging<CustomerSearchResponse> searchCustomers(CustomerSearchRequest params) {
        
        //Page, sort 정보를 생성
        PageRequest pageRequest = PageRequest.of(params.getPage(), params.getPageSize(), Sort.by("id"));
        //검색조건 생성
        Specification<Customer> specification = CustomerSpecification.searchWith(params);
        Page<Customer> customers = customerRepository.findAll(specification, pageRequest);

        //Customer => CustomerSearchResponse 변환
        List<CustomerSearchResponse> list = customers.getContent()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerSearchResponse.class))
                .collect(Collectors.toList());

        //Paging 생성하여 반환
        return new Paging<>(customers.getTotalPages(), customers.getTotalElements(), list);
    }

    /**
     * 고객정보 저장
     * @param customer
     */
    @Transactional
    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

}
