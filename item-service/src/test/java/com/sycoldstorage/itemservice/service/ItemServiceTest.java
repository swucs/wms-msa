package com.sycoldstorage.itemservice.service;

import com.sycoldstorage.itemservice.dto.SearchItemRequest;
import com.sycoldstorage.itemservice.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    @DisplayName("품목목록검색")
    void searchItems() {
        SearchItemRequest params = SearchItemRequest.builder()
                .build();

        List<Item> items = itemService.searchItems(params);

        assertThat(items).isNotNull();
        assertThat(items.size()).isGreaterThan(0);


        params = SearchItemRequest.builder()
                .name("닭꼬치")
                .build();

        items = itemService.searchItems(params);

        assertThat(items).isNotNull();
        assertThat(items.size()).isEqualTo(1);
        assertThat(items.get(0).getName()).isEqualTo("닭꼬치");

    }
}