package com.example.crud.repository;

import com.example.crud.domain.Shop;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface ShopJpaRepository extends JpaRepository<Shop, Integer>,ShopCustomRepository {

}
