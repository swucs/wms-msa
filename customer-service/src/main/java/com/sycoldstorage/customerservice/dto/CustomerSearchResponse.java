package com.sycoldstorage.customerservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CustomerSearchResponse {

    private int id;
    private String name;
    private String merchantNumber;
    private String representativeName;
    private String BusinessConditions;
    private String typeOfBusiness;
    private String phoneNumber;
    private String faxNumber;
    private boolean use;

    private String getUseYn() {
        return use ? "사용" : "미사용";
    }

}
