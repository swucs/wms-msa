package com.sycoldstorage.customerservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class SaveCustomerResponse {
    private Long id;
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
