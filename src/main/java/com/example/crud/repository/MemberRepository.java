package com.example.crud.repository;

import com.example.crud.domain.Member;
import com.example.crud.domain.QMember;
import com.example.crud.enums.Gender;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.crud.domain.QMember.member;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    private final JPQLQueryFactory queryFactory;

    public Page<Member> getQueryFactory(String name, String gender,  Pageable pageable) {
        QMember member = QMember.member;

        long total = queryFactory
                .selectFrom(member)
                .where(
                        MemberName(name),
                        MemberGender(gender)
                )
                .fetchCount();

       List<Member> members = queryFactory
                .selectFrom(member)
                .where(
                        MemberName(name),
                        MemberGender(gender)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        return new PageImpl<>(members, pageable, total);
    }

    private BooleanExpression MemberName(String name) {
        return hasText(name) ? member.name.eq(name):null;
    }
    private BooleanExpression MemberGender(String gender) {
        return hasText(gender) ? member.gender.eq(Gender.valueOf(gender)) : null;
    }
}
