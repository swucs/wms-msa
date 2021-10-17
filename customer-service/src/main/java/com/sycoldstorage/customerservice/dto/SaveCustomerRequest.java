package com.sycoldstorage.customerservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SaveCustomerRequest {
    private Long id;
    private String name;
    private String businessNumber;
    private String representativeName;
    private String businessConditions;
    private String typeOfBusiness;
    private String address;
    private String phoneNumber;
    private String faxNumber;
    private String useYn;

    public boolean isUse() {
        if ("Y".equals(useYn)) {
            return true;
        }
        return false;
    }
}
