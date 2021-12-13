package com.sycoldstorage.itemservice.repository;

import com.sycoldstorage.itemservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 품목 Repository
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByName(String name);
}
