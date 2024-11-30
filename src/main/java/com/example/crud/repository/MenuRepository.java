package com.example.crud.repository;

import com.example.crud.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long>,MenuCustomRepository{

    Menu findByShopId(Integer shopId);

}
