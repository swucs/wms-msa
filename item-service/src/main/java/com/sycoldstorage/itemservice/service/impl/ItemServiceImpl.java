package com.sycoldstorage.itemservice.service.impl;

import com.sycoldstorage.itemservice.dto.SearchItemRequest;
import com.sycoldstorage.itemservice.entity.Item;
import com.sycoldstorage.itemservice.repository.ItemRepository;
import com.sycoldstorage.itemservice.service.ItemService;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 품목 Service
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 품목 목록 검색
     * @param params
     * @return
     */
    @Override
    public List<Item> searchItems(SearchItemRequest params) {

        List<Item> items;
        if (StringUtils.isNotBlank(params.getName())) {
            items = itemRepository.findByName(params.getName());
        } else {
            items = itemRepository.findAll();
        }

        return items;
    }


    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

}
