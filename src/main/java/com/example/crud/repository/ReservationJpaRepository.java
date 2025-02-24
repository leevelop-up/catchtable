package com.example.crud.repository;

import com.example.crud.domain.Reservation;
import com.example.crud.dto.reservation.ReservationSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationJpaRepository extends JpaRepository<Reservation, Integer> {

    Page<Reservation> findByMemberNo(Integer memberId, Pageable pageable);
}
