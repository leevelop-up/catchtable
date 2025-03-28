package com.example.crud.repository;


import com.example.crud.domain.QShop;
import com.example.crud.domain.Shop;
import com.example.crud.enums.Category;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShopCustomRepositoryImpl implements ShopCustomRepository {

    private final JPQLQueryFactory queryFactory;

    @Override
    public Page<Shop> search(String name, String city, String district, Category Category, Pageable pageable) {
        QShop shop = QShop.shop;

        long total = queryFactory
                .selectFrom(shop)
                .where(
                        shopName(name),
                        shopCity(city),
                        shopCategory(Category),
                        shopDistrict(district)
                )
                .fetchCount();

        List<Shop> shops = queryFactory
                .selectFrom(shop)
                .where(
                        shopName(name),
                        shopCity(city),
                        shopCategory(Category),
                        shopDistrict(district)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        return new PageImpl<>(shops, pageable, total);
    }

    private Predicate shopDistrict(String district) {
        return district != null ? QShop.shop.district.contains(district) : null;
    }

    private Predicate shopCategory(Category category) {
        return category != null ? QShop.shop.category.eq(category) : null;
    }

    private Predicate shopCity(String city) {
        return city != null ? QShop.shop.city.contains(city) : null;
    }

    private Predicate shopName(String name) {
        return name != null ? QShop.shop.name.contains(name) : null;
    }


}
