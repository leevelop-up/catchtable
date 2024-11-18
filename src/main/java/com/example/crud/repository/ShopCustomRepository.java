package com.example.crud.repository;

import com.example.crud.domain.Shop;
import com.example.crud.dto.param.ShopSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public interface ShopCustomRepository {
    Page<Shop> search(ShopSearchParam shopSearchParam, Pageable pageable);
}
