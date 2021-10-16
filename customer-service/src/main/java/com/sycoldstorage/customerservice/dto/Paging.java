package com.sycoldstorage.customerservice.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Paging<T> {
    private int totalPages;
    private long totalCount;
    private List<T> content;
}
