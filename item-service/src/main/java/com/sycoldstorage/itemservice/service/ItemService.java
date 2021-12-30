package com.sycoldstorage.itemservice.service;

import com.sycoldstorage.itemservice.dto.SearchItemRequest;
import com.sycoldstorage.itemservice.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> searchItems(SearchItemRequest params);

    Item createItem(Item item);
}
