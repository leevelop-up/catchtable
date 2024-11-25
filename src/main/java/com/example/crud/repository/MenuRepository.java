package com.example.crud.repository;

import com.example.crud.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu findByShopId(Integer shopId);
}
