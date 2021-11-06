package com.sycoldstorage.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sycoldstorage.customerservice.annotation.EnableMockMvc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@EnableMockMvc  //응답내용 Encoding을 위한
@ActiveProfiles("test") //application.properties 파일을 공용으로 사용하고 application-test.properties에서 Override한다.
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("고객목록")
    void getCustomers() throws Exception {

        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("page", "1");
        requestParam.set("pageSize", "10");

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaTypes.HAL_JSON)
                        .queryParams(requestParam))
                .andDo(print())

        ;
    }
}