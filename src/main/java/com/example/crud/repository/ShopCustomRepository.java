package com.example.crud.repository;

import com.example.crud.domain.Shop;
import com.example.crud.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public interface ShopCustomRepository {
    Page<Shop> search(String name, String city, String district, Category category, Pageable pageable);
}
