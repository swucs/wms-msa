package com.sycoldstorage.customerservice.dto;

import lombok.*;

/**
 * 고객 리스트 화면에서 검색 요청
 */
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearchRequest {

    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer id;
    private String useYn;

}
