package com.example.crud.repository;

import com.example.crud.domain.Menu;
import com.example.crud.domain.QMenu;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuCustomRepositoryImpl implements MenuCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Menu> search(Integer shopId, String name, long price, String description, Pageable pageable) {
        QMenu menu = QMenu.menu;

        // ✅ BooleanBuilder를 사용하여 동적 조건 생성
        BooleanBuilder builder = new BooleanBuilder();
        if (shopId != null) {
            builder.and(menu.shop.id.eq(shopId));
        }
        if (name != null && !name.isEmpty()) {
            builder.and(menu.name.containsIgnoreCase(name));
        }
        if (price > 0) { // ✅ price 기본값이 0이면 검색 조건을 적용하지 않음
            builder.and(menu.price.eq(price));
        }
        if (description != null && !description.isEmpty()) {
            builder.and(menu.description.containsIgnoreCase(description));
        }

        // ✅ 총 개수 조회 (fetchCount()는 Deprecated → fetchOne() 사용)
        long total = queryFactory
                .select(menu.count())
                .from(menu)
                .where(builder)
                .fetchOne(); // ✅ 개수 조회 개선

        // ✅ 페이징 처리
        List<Menu> menus = queryFactory
                .selectFrom(menu)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(menus, pageable, total);
    }
}
