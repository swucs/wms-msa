package com.sycoldstorage.itemservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sycoldstorage.itemservice.annotation.EnableMockMvc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableMockMvc  //응답내용 Encoding을 위한
@AutoConfigureRestDocs
class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("품목 목록")
    void queryItems() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/items"))
                .andDo(print())
                .andExpect(status().isOk())
                ;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/items")
                        .param("name", "닭꼬치")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .characterEncoding("UTF-8")
                        .accept(MediaTypes.HAL_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }
}