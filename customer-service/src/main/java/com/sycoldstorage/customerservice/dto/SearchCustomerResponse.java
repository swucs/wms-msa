package com.sycoldstorage.customerservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchCustomerResponse {

    private int id;
    private String name;
    private String businessNumber;
    private String representativeName;
    private String businessConditions;
    private String typeOfBusiness;
    private String address;
    private String phoneNumber;
    private String faxNumber;
    private boolean use;
}
