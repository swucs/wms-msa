package com.sycoldstorage.customerservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 고객 Entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "customer")
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String merchantNumber;
    private String representativeName;
    private String BusinessConditions;
    private String typeOfBusiness;
    private String address;
    private String phoneNumber;
    private String faxNumber;
    private boolean use;
}
