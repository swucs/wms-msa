package com.sycoldstorage.itemservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchItemResponse {

    private Long id;
    private String name;
    private double unitWeight;
    private String unitName;
    private LocalDateTime registerDate;
    private String remarks;
}
