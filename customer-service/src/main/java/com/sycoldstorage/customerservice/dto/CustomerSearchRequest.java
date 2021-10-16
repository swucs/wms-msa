package com.sycoldstorage.customerservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 고객 리스트 화면에서 검색 요청
 */
@Builder
@Getter
@ToString
public class CustomerSearchRequest {

    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer id;
    private String useYn;

}
