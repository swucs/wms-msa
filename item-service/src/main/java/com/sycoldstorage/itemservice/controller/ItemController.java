package com.sycoldstorage.itemservice.controller;

import com.sycoldstorage.itemservice.dto.SearchItemRequest;
import com.sycoldstorage.itemservice.dto.SearchItemResponse;
import com.sycoldstorage.itemservice.entity.Item;
import com.sycoldstorage.itemservice.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 품목 목록 검색
     * @param params
     * @return
     */
    @GetMapping("/items")
    public ResponseEntity queryItems(@ModelAttribute SearchItemRequest params) {
        List<Item> items = itemService.searchItems(params);

        List<SearchItemResponse> itemResponses = items.stream()
                .map(v -> modelMapper.map(v, SearchItemResponse.class))
//                .map(v -> EntityModel.of(v)
//                                .add(linkTo())
//                                )
                .collect(Collectors.toList());
        ;

        return ResponseEntity.ok().body(itemResponses);

    }

}
