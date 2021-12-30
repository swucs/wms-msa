package com.sycoldstorage.itemservice.service;

import com.sycoldstorage.itemservice.dto.SearchItemRequest;
import com.sycoldstorage.itemservice.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
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

    @Test
    @DisplayName("품목신규생성")
    void createItem() {
        Item item = Item.builder()
                .name("품목신규")
                .unitWeight(3.1)
                .unitName("상자")
                .registerdDate(LocalDateTime.now())
                .build();

        Item savedItem = itemService.createItem(item);

        assertThat(savedItem).isNotNull();
        assertThat(savedItem.getName()).isEqualTo("닭꼬치");
        assertThat(savedItem.getUnitWeight()).isEqualTo(3.1);
        assertThat(savedItem.getUnitName()).isEqualTo("상자");
        assertThat(savedItem.getRegisterdDate()).isNotNull();


    }
}