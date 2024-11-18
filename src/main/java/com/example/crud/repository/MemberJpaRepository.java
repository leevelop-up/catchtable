package com.example.crud.repository;

import com.example.crud.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface MemberJpaRepository extends JpaRepository<Member, Integer> {

    Page<Member> findByNameContaining(String keyword, Pageable page);
    @Query("SELECT c FROM Member c WHERE c.name = :name")
    Page<Member> findBySearchName(@Param("name") String Name, Pageable page);

    /*querydsl 사용시

    */


}
