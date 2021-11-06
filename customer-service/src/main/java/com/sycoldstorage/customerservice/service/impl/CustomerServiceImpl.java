package com.sycoldstorage.customerservice.service.impl;

import com.sycoldstorage.customerservice.dto.SaveCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerResponse;
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
import java.util.Optional;
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
    public Page<SearchCustomerResponse> searchCustomers(SearchCustomerRequest params) {
        
        //Page, sort 정보를 생성
        PageRequest pageRequest = PageRequest.of(params.getPage(), params.getPageSize(), Sort.by("id"));
        //검색조건 생성
        Specification<Customer> specification = CustomerSpecification.searchWith(params);
        Page<Customer> customers = customerRepository.findAll(specification, pageRequest);

        //Customer => CustomerSearchResponse 변환
        return customers.map(v -> modelMapper.map(v, SearchCustomerResponse.class));
    }

    /**
     * 고객정보 저장
     * @param saveCustomerRequest
     * @return
     */
    @Transactional
    @Override
    public long save(SaveCustomerRequest saveCustomerRequest) {

        Long id = saveCustomerRequest.getId();

        if (id == null) {
            Customer customerRequest = modelMapper.map(saveCustomerRequest, Customer.class);
            Customer customer = customerRepository.save(customerRequest);
            id = customer.getId();

        } else {

            Optional<Customer> customerOptional = customerRepository.findById(id);

            if (customerOptional.isPresent()) {
                //request값을 customer로 매핑
                Customer customer = customerOptional.get();
                modelMapper.map(saveCustomerRequest, customer);
                System.out.println("customer : " + customer);
            } else {
                throw new RuntimeException("There is no such ID : " + id);
            }
        }
        return id;
    }

}
