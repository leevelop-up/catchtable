package com.example.crud.repository;

import com.example.crud.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ShopJpaRepository extends JpaRepository<Shop, Integer>,ShopCustomRepository {

}
