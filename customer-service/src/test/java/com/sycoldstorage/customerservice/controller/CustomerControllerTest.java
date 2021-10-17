package com.sycoldstorage.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sycoldstorage.customerservice.annotation.EnableMockMvc;
import com.sycoldstorage.customerservice.dto.CustomerSearchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@EnableMockMvc
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getCustomers() throws Exception {

        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("page", "1");
        requestParam.set("pageSize", "10");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaTypes.HAL_JSON)
                        .queryParams(requestParam))
                .andDo(print());
    }
}