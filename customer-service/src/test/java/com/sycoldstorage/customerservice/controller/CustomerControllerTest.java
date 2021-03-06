package com.sycoldstorage.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sycoldstorage.customerservice.annotation.EnableMockMvc;
import com.sycoldstorage.customerservice.dto.SaveCustomerRequest;
import com.sycoldstorage.customerservice.entity.Customer;
import com.sycoldstorage.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableMockMvc  //???????????? Encoding??? ??????
@ActiveProfiles("test") //application.properties ????????? ???????????? ???????????? application-test.properties?????? Override??????.
@AutoConfigureRestDocs
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @DisplayName("???????????????")
    void getCustomers() throws Exception {


//        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
//        requestParam.set("page", "1");
//        requestParam.set("pageSize", "10");

        final String prefix = "_embedded.searchCustomerResponseList[].";

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/customers")
                        .param("page", "1")
                        .param("pageSize", "10")
                        .param("name", "")
                        .param("id", "")
                        .param("useYn", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaTypes.HAL_JSON)
//                        .queryParams(requestParam)
                    )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("page").exists())
                .andExpect(jsonPath("_embedded.searchCustomerResponseList[0]._links.self.href").exists())
                .andExpect(jsonPath("_links.self.href").exists())
                .andExpect(jsonPath("_links.profile.href").exists())
                //rest docs
                .andDo(document("query-customers"
                        , links(
                                linkWithRel("self").description("link to self")
                                , linkWithRel("first").description("????????? ????????? ???????????? ??????")
                                , linkWithRel("prev").description("?????? ????????? ???????????? ??????")
                                , linkWithRel("next").description("?????? ????????? ???????????? ??????")
                                , linkWithRel("last").description("????????? ????????? ???????????? ??????")
                                , linkWithRel("profile").description("???????????? ???????????? ??????")
                        )
                        , requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header ??????")
                                , headerWithName(HttpHeaders.CONTENT_TYPE).description("content type ??????")
                        )
                        , requestParameters(
                                parameterWithName("page").description("??????????????? page ??????")
                                , parameterWithName("pageSize").description("????????? ??????")
                                , parameterWithName("name").description("??????????????? ?????????(like ??????)")
                                , parameterWithName("id").description("??????????????? ??????ID")
                                , parameterWithName("useYn").description("??????????????? ????????????")
                        )
                        , responseHeaders(
//                                headerWithName(HttpHeaders.LOCATION).description("location header")
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type : Hal Json Type")
                        )

                        , responseFields(
                                fieldWithPath(prefix + "id").description("??????ID")
                                , fieldWithPath(prefix + "name").description("?????????")
                                , fieldWithPath(prefix + "businessNumber").description("?????????????????????")
                                , fieldWithPath(prefix + "representativeName").description("????????????")
                                , fieldWithPath(prefix + "typeOfBusiness").description("??????")
                                , fieldWithPath(prefix + "address").description("??????")
                                , fieldWithPath(prefix + "phoneNumber").description("????????????")
                                , fieldWithPath(prefix + "faxNumber").description("fax??????")
                                , fieldWithPath(prefix + "use").description("????????????")
                                , fieldWithPath(prefix + "businessConditions").description("??????")
                                , fieldWithPath(prefix + "_links.self.href").description("?????? ???????????? ???????????? ??????")
                                , fieldWithPath("_links.self.href").description("?????? ?????? ????????????")
                                , fieldWithPath("_links.first.href").description("?????? ?????? ????????????")
                                , fieldWithPath("_links.prev.href").description("?????? ?????? ????????????")
                                , fieldWithPath("_links.next.href").description("?????? ?????? ????????????")
                                , fieldWithPath("_links.last.href").description("?????? ?????? ????????????")
                                , fieldWithPath("_links.profile.href").description("?????? ?????? ????????????")
                                , fieldWithPath("page.size").description("?????? ?????? ????????????")
                                , fieldWithPath("page.totalElements").description("?????? ?????? ????????????")
                                , fieldWithPath("page.totalElements").description("?????? ?????? ????????????")
                                , fieldWithPath("page.totalPages").description("?????? ?????? ????????????")
                                , fieldWithPath("page.number").description("?????? ?????? ????????????")
                        )
                    )
                )

        ;
    }

    @Test
    @DisplayName("??????????????? ??????")
    @Transactional
    @Rollback
    void createCustomer() throws Exception {

        SaveCustomerRequest createRequest = SaveCustomerRequest.builder()
                .name("JUNIT ?????????")
                .address("??????")
                .businessConditions("test")
                .faxNumber("????????????")
                .phoneNumber("????????????")
                .representativeName("????????????")
                .businessNumber("???????????????")
                .useYn("Y")
                .typeOfBusiness("test")
                .build();


        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaTypes.HAL_JSON)
                        .content(this.objectMapper.writeValueAsString(createRequest))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                //rest docs
                .andDo(document("customer-create"
                        , links(
                                linkWithRel("self").description("link to self")
                                , linkWithRel("list").description("????????? ?????? ??????")
                                , linkWithRel("profile").description("???????????? ???????????? ??????")
                        )
                        , requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header ??????")
                                , headerWithName(HttpHeaders.CONTENT_TYPE).description("content type ??????")
                        )
                        , requestFields(
                                fieldWithPath("id").description("??????ID")
                                , fieldWithPath("name").description("?????????")
                                , fieldWithPath("businessNumber").description("?????????????????????")
                                , fieldWithPath("representativeName").description("????????????")
                                , fieldWithPath("typeOfBusiness").description("??????")
                                , fieldWithPath("address").description("??????")
                                , fieldWithPath("phoneNumber").description("????????????")
                                , fieldWithPath("faxNumber").description("fax??????")
                                , fieldWithPath("useYn").description("????????????")
                                , fieldWithPath("businessConditions").description("??????")
                        )
                        , responseHeaders(
//                                headerWithName(HttpHeaders.LOCATION).description("location header")
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type : Hal Json Type")
                        )

                        , responseFields(
                                fieldWithPath("id").description("??????ID")
                                , fieldWithPath("name").description("?????????")
                                , fieldWithPath("businessNumber").description("?????????????????????")
                                , fieldWithPath("representativeName").description("????????????")
                                , fieldWithPath("typeOfBusiness").description("??????")
                                , fieldWithPath("address").description("??????")
                                , fieldWithPath("phoneNumber").description("????????????")
                                , fieldWithPath("faxNumber").description("fax??????")
                                , fieldWithPath("use").description("????????????")
                                , fieldWithPath("businessConditions").description("??????")
                                , fieldWithPath("_links.self.href").description("????????? ???????????? ???????????? ?????? ??????")
                                , fieldWithPath("_links.list.href").description("????????? ?????? ????????????")
                                , fieldWithPath("_links.profile.href").description("????????? ?????? profile ????????????")
                        )
                    )
                )
        ;

    }


    @Test
    @DisplayName("??????????????? ??????")
    void updateCustomer() throws Exception {

        Optional<Customer> customerOptional = customerRepository.findById(47l);
//        SaveCustomerRequest updateCustomerRequest = this.modelMapper.map(customerOptional.get(), SaveCustomerRequest.class);


        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/customer/{id}", customerOptional.get().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaTypes.HAL_JSON)
                                .content(this.objectMapper.writeValueAsString(customerOptional.get()))
                        )
                .andDo(print())
                .andExpect(status().isOk())
                //rest docs
                .andDo(document("customer-create"
                        , links(
                                linkWithRel("self").description("link to self")
                                , linkWithRel("list").description("????????? ?????? ??????")
                                , linkWithRel("profile").description("???????????? ???????????? ??????")
                        )
                        , requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header ??????")
                                , headerWithName(HttpHeaders.CONTENT_TYPE).description("content type ??????")
                        )
                        , requestFields(
                                fieldWithPath("id").description("??????ID")
                                , fieldWithPath("name").description("?????????")
                                , fieldWithPath("businessNumber").description("?????????????????????")
                                , fieldWithPath("representativeName").description("????????????")
                                , fieldWithPath("typeOfBusiness").description("??????")
                                , fieldWithPath("address").description("??????")
                                , fieldWithPath("phoneNumber").description("????????????")
                                , fieldWithPath("faxNumber").description("fax??????")
                                , fieldWithPath("useYn").description("????????????")
                                , fieldWithPath("businessConditions").description("??????")
                        )
                        , responseHeaders(
//                                headerWithName(HttpHeaders.LOCATION).description("location header")
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type : Hal Json Type")
                        )

                        , responseFields(
                                fieldWithPath("id").description("??????ID")
                                , fieldWithPath("name").description("?????????")
                                , fieldWithPath("businessNumber").description("?????????????????????")
                                , fieldWithPath("representativeName").description("????????????")
                                , fieldWithPath("typeOfBusiness").description("??????")
                                , fieldWithPath("address").description("??????")
                                , fieldWithPath("phoneNumber").description("????????????")
                                , fieldWithPath("faxNumber").description("fax??????")
                                , fieldWithPath("use").description("????????????")
                                , fieldWithPath("businessConditions").description("??????")
                                , fieldWithPath("_links.self.href").description("????????? ???????????? ???????????? ?????? ??????")
                                , fieldWithPath("_links.list.href").description("????????? ?????? ????????????")
                                , fieldWithPath("_links.profile.href").description("????????? ?????? profile ????????????")
                        )
                    )
                )
                ;

    }

    @Test
    @DisplayName("?????????????????????_400??????")
    void updateCustomer_400error() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/customer/999999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaTypes.HAL_JSON)
                        .content(this.objectMapper.writeValueAsString(SaveCustomerRequest.builder().id(999999l).build()))
                )
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

    }

}