package com.sycoldstorage.itemservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchItemRequest {
    private String name;
}
