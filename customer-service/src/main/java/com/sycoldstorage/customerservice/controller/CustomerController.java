package com.sycoldstorage.customerservice.controller;

import com.sycoldstorage.customerservice.dto.SearchCustomerRequest;
import com.sycoldstorage.customerservice.dto.SearchCustomerResponse;
import com.sycoldstorage.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity queryCustomers(@ModelAttribute SearchCustomerRequest params, PagedResourcesAssembler<SearchCustomerResponse> assembler) {
        Page<SearchCustomerResponse> customers = customerService.searchCustomers(params);


        CollectionModel<EntityModel<SearchCustomerResponse>> entityModels = assembler
                //Page 정보를 이용해서 Page의 _link를 생성하고 List의 각 element마다 _links.self 를 생성
                .toModel(customers
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

}
