package com.sycoldstorage.customerservice.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 거래처 Entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of="id")
@Entity(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String businessNumber;

    @Column(nullable = false)
    private String representativeName;

    private String businessConditions;

    private String typeOfBusiness;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    private String faxNumber;

    @Column(nullable = false)
    private boolean use;

    @Column(nullable = false)
    private boolean deleted;
}
