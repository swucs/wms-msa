package com.sycoldstorage.itemservice.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

/**
 * 품목 Entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of="id")
@Entity(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double unitWeight;

    @Column(nullable = false)
    private String unitName;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registerdDate;

    private String remarks;
}
