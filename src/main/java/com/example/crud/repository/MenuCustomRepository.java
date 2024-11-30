package com.example.crud.repository;

import com.example.crud.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuCustomRepository {

    Page<Menu> search(String name, long price, String description, Pageable pageable);
}
