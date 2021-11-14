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
@EnableMockMvc  //응답내용 Encoding을 위한
@ActiveProfiles("test") //application.properties 파일을 공용으로 사용하고 application-test.properties에서 Override한다.
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
    @DisplayName("거래처목록")
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
                                , linkWithRel("first").description("첫번째 페이지 조회하는 링크")
                                , linkWithRel("prev").description("이전 페이지 조회하는 링크")
                                , linkWithRel("next").description("다음 페이지 조회하는 링크")
                                , linkWithRel("last").description("마지막 페이지 조회하는 링크")
                                , linkWithRel("profile").description("프로필로 이동하는 링크")
                        )
                        , requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header 명시")
                                , headerWithName(HttpHeaders.CONTENT_TYPE).description("content type 명시")
                        )
                        , requestParameters(
                                parameterWithName("page").description("이동하려는 page 번호")
                                , parameterWithName("pageSize").description("페이지 크기")
                                , parameterWithName("name").description("검색하려는 업체명(like 검색)")
                                , parameterWithName("id").description("검색하려는 업체ID")
                                , parameterWithName("useYn").description("검색하려는 사용여부")
                        )
                        , responseHeaders(
//                                headerWithName(HttpHeaders.LOCATION).description("location header")
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type : Hal Json Type")
                        )

                        , responseFields(
                                fieldWithPath(prefix + "id").description("업체ID")
                                , fieldWithPath(prefix + "name").description("업체명")
                                , fieldWithPath(prefix + "businessNumber").description("사업자등록번호")
                                , fieldWithPath(prefix + "representativeName").description("대표자명")
                                , fieldWithPath(prefix + "typeOfBusiness").description("업종")
                                , fieldWithPath(prefix + "address").description("주소")
                                , fieldWithPath(prefix + "phoneNumber").description("전화번호")
                                , fieldWithPath(prefix + "faxNumber").description("fax번호")
                                , fieldWithPath(prefix + "use").description("사용여부")
                                , fieldWithPath(prefix + "businessConditions").description("업태")
                                , fieldWithPath(prefix + "_links.self.href").description("해당 데이터의 상세정보 링크")
                                , fieldWithPath("_links.self.href").description("현재 화면 링크정보")
                                , fieldWithPath("_links.first.href").description("현재 화면 링크정보")
                                , fieldWithPath("_links.prev.href").description("현재 화면 링크정보")
                                , fieldWithPath("_links.next.href").description("현재 화면 링크정보")
                                , fieldWithPath("_links.last.href").description("현재 화면 링크정보")
                                , fieldWithPath("_links.profile.href").description("현재 화면 링크정보")
                                , fieldWithPath("page.size").description("현재 화면 링크정보")
                                , fieldWithPath("page.totalElements").description("현재 화면 링크정보")
                                , fieldWithPath("page.totalElements").description("현재 화면 링크정보")
                                , fieldWithPath("page.totalPages").description("현재 화면 링크정보")
                                , fieldWithPath("page.number").description("현재 화면 링크정보")
                        )
                    )
                )

        ;
    }

    @Test
    @DisplayName("거래처정보 생성")
    @Transactional
    @Rollback
    void createCustomer() throws Exception {

        SaveCustomerRequest createRequest = SaveCustomerRequest.builder()
                .name("JUNIT 테스트")
                .address("주소")
                .businessConditions("test")
                .faxNumber("팩스번호")
                .phoneNumber("전화번호")
                .representativeName("대표자명")
                .businessNumber("사업자번호")
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
                                , linkWithRel("list").description("리스트 조회 링크")
                                , linkWithRel("profile").description("프로필로 이동하는 링크")
                        )
                        , requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header 명시")
                                , headerWithName(HttpHeaders.CONTENT_TYPE).description("content type 명시")
                        )
                        , requestFields(
                                fieldWithPath("id").description("업체ID")
                                , fieldWithPath("name").description("업체명")
                                , fieldWithPath("businessNumber").description("사업자등록번호")
                                , fieldWithPath("representativeName").description("대표자명")
                                , fieldWithPath("typeOfBusiness").description("업종")
                                , fieldWithPath("address").description("주소")
                                , fieldWithPath("phoneNumber").description("전화번호")
                                , fieldWithPath("faxNumber").description("fax번호")
                                , fieldWithPath("useYn").description("사용여부")
                                , fieldWithPath("businessConditions").description("업태")
                        )
                        , responseHeaders(
//                                headerWithName(HttpHeaders.LOCATION).description("location header")
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type : Hal Json Type")
                        )

                        , responseFields(
                                fieldWithPath("id").description("업체ID")
                                , fieldWithPath("name").description("업체명")
                                , fieldWithPath("businessNumber").description("사업자등록번호")
                                , fieldWithPath("representativeName").description("대표자명")
                                , fieldWithPath("typeOfBusiness").description("업종")
                                , fieldWithPath("address").description("주소")
                                , fieldWithPath("phoneNumber").description("전화번호")
                                , fieldWithPath("faxNumber").description("fax번호")
                                , fieldWithPath("use").description("사용여부")
                                , fieldWithPath("businessConditions").description("업태")
                                , fieldWithPath("_links.self.href").description("생성된 데이터의 상세정보 조회 링크")
                                , fieldWithPath("_links.list.href").description("리스트 조회 링크정보")
                                , fieldWithPath("_links.profile.href").description("거래처 등록 profile 링크정보")
                        )
                    )
                )
        ;

    }


    @Test
    @DisplayName("거래처정보 수정")
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
                                , linkWithRel("list").description("리스트 조회 링크")
                                , linkWithRel("profile").description("프로필로 이동하는 링크")
                        )
                        , requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header 명시")
                                , headerWithName(HttpHeaders.CONTENT_TYPE).description("content type 명시")
                        )
                        , requestFields(
                                fieldWithPath("id").description("업체ID")
                                , fieldWithPath("name").description("업체명")
                                , fieldWithPath("businessNumber").description("사업자등록번호")
                                , fieldWithPath("representativeName").description("대표자명")
                                , fieldWithPath("typeOfBusiness").description("업종")
                                , fieldWithPath("address").description("주소")
                                , fieldWithPath("phoneNumber").description("전화번호")
                                , fieldWithPath("faxNumber").description("fax번호")
                                , fieldWithPath("useYn").description("사용여부")
                                , fieldWithPath("businessConditions").description("업태")
                        )
                        , responseHeaders(
//                                headerWithName(HttpHeaders.LOCATION).description("location header")
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type : Hal Json Type")
                        )

                        , responseFields(
                                fieldWithPath("id").description("업체ID")
                                , fieldWithPath("name").description("업체명")
                                , fieldWithPath("businessNumber").description("사업자등록번호")
                                , fieldWithPath("representativeName").description("대표자명")
                                , fieldWithPath("typeOfBusiness").description("업종")
                                , fieldWithPath("address").description("주소")
                                , fieldWithPath("phoneNumber").description("전화번호")
                                , fieldWithPath("faxNumber").description("fax번호")
                                , fieldWithPath("use").description("사용여부")
                                , fieldWithPath("businessConditions").description("업태")
                                , fieldWithPath("_links.self.href").description("생성된 데이터의 상세정보 조회 링크")
                                , fieldWithPath("_links.list.href").description("리스트 조회 링크정보")
                                , fieldWithPath("_links.profile.href").description("거래처 등록 profile 링크정보")
                        )
                    )
                )
                ;

    }

    @Test
    @DisplayName("거래처정보수정_400에러")
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