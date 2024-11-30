package com.example.crud.repository;


import com.example.crud.domain.Menu;
import com.example.crud.domain.QMenu;
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
public class MenuCustomRepositoryImpl implements MenuCustomRepository {

    private final JPQLQueryFactory queryFactory;
    @Override
    public Page<Menu> search(String name, long price, String description, Pageable pageable) {
        QMenu menu = QMenu.menu;
        long total = queryFactory
                .selectFrom(menu)
                .where(
                        menuName(name),
                        menuPrice(price),
                        menuDescription(description)
                )
                .fetchCount();

        List<Menu> menus = queryFactory
                .selectFrom(menu)
                .where(
                        menuName(name),
                        menuPrice(price),
                        menuDescription(description)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(menus, pageable, total);
    }

    private Predicate menuDescription(String description) {
        return description != null ? QMenu.menu.description.contains(description) : null;
    }

    private Predicate menuPrice(long price) {
        return price != 0 ? QMenu.menu.price.eq(price) : null;
    }

    private Predicate menuName(String name) {
        return name != null ? QMenu.menu.name.contains(name) : null;
    }
}
