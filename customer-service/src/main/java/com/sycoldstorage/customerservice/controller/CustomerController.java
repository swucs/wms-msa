package com.sycoldstorage.customerservice.controller;

import com.sycoldstorage.customerservice.dto.SaveCustomerRequest;
import com.sycoldstorage.customerservice.dto.SaveCustomerResponse;
import com.sycoldstorage.customerservice.dto.SearchCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerResponse;
import com.sycoldstorage.customerservice.entity.Customer;
import com.sycoldstorage.customerservice.exception.NoSuchDataException;
import com.sycoldstorage.customerservice.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 거래처 목록 조회
     * @param params
     * @param assembler
     * @return
     */
    @GetMapping("/customers")
    public ResponseEntity queryCustomers(@ModelAttribute SearchCustomerRequest params, PagedResourcesAssembler<SearchCustomerResponse> assembler) {
        Page<Customer> customers = customerService.searchCustomers(params);

        //Customer => CustomerSearchResponse 변환
        Page<SearchCustomerResponse> searchCustomerResponses = customers.map(v -> modelMapper.map(v, SearchCustomerResponse.class));

        CollectionModel<EntityModel<SearchCustomerResponse>> entityModels = assembler
                //Page 정보를 이용해서 Page의 _link를 생성하고 List의 각 element마다 _links.self 를 생성
                .toModel(searchCustomerResponses
                        , entity -> EntityModel.of(entity,
                                linkTo(
                                        methodOn(CustomerController.class)
                                                .queryCustomers(null, null)
                                )
                                .slash(entity.getId())
                                .withSelfRel()
                        ))
                //_links.profile을 생성
                .add(Link.of("/docs/index.html#resources-customers-list").withRel("profile"));

        return ResponseEntity.ok().body(entityModels);
    }

    /**
     * 거래처정보 등록
     * @param request
     * @param errors
     * @return
     */
    @PostMapping("/customer")
    public ResponseEntity createCustomer(@RequestBody @Validated SaveCustomerRequest request,
                                         Errors errors) {
        Customer createdCustomer = customerService.create(request);

        EntityModel<SaveCustomerResponse> entityModel = EntityModel.of(modelMapper.map(createdCustomer, SaveCustomerResponse.class))
                .add(linkTo(methodOn(CustomerController.class).createCustomer(null, null)).withSelfRel())
                .add(linkTo(methodOn(CustomerController.class).queryCustomers(null, null)).withRel("list"))
                .add(Link.of("/docs/index.html#resources-customer-create").withRel("profile"));

        URI createdUri = linkTo(methodOn(CustomerController.class).queryCustomers(null, null)).toUri();

        return ResponseEntity.created(createdUri).body(entityModel);
    }


    /**
     * 거래처정보 수정
     * @param id
     * @param request
     * @param errors
     * @return
     */
    @PutMapping("/customer/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id,
                                         @RequestBody @Validated SaveCustomerRequest request,
                                         Errors errors) {


        Customer updatedCustomer = null;
        try {
            updatedCustomer = customerService.update(request);
        } catch (NoSuchDataException e) {
            //데이터가 없는 경우
            return ResponseEntity.notFound().build();
        }

        EntityModel<Customer> entityModel = EntityModel.of(updatedCustomer)
                .add(linkTo(methodOn(CustomerController.class).updateCustomer(id, null, null)).withSelfRel())
                .add(linkTo(methodOn(CustomerController.class).queryCustomers(null, null)).withRel("list"))
                .add(Link.of("/docs/index.html#resources-customer-update").withRel("profile"));

        return ResponseEntity.ok(entityModel);

    }
    
    
    /**
     * 거래처정보 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/customer/{id}")
    public ResponseEntity deleteCustomer(@PathVariable long id) {


        Customer deletedCustomer = null;
        try {
            deletedCustomer = customerService.delete(id);
        } catch (NoSuchDataException e) {
            //데이터가 없는 경우
            return ResponseEntity.notFound().build();
        }

        EntityModel<Customer> entityModel = EntityModel.of(deletedCustomer)
                .add(linkTo(methodOn(CustomerController.class).deleteCustomer(id)).withSelfRel())
                .add(linkTo(methodOn(CustomerController.class).queryCustomers(null, null)).withRel("list"))
                .add(Link.of("/docs/index.html#resources-customer-delete").withRel("profile"));

        return ResponseEntity.ok(entityModel);
    }

}
