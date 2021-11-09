package com.sycoldstorage.customerservice.dto;

import lombok.*;

/**
 * 거래처 리스트 화면에서 검색 요청
 */
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchCustomerRequest {

    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer id;
    private String useYn;

}
