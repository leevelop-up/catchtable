package com.example.crud.service;

import com.example.crud.domain.Reservation;
import com.example.crud.dto.reservation.ReservationRegisterParam;
import com.example.crud.dto.reservation.ReservationUpdateParam;
import com.example.crud.dto.reservation.ReservationSearchRequest;
import com.example.crud.dto.response.ApiResponse;
import com.example.crud.exception.CrudException;
import com.example.crud.repository.ReservationJpaRepository;
import com.example.crud.repository.ShopJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.crud.exception.ErrorCode.VALUE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationJpaRepository reservationJpaRepository;
    private final ShopJpaRepository shopJpaRepository;
    public ApiResponse<?> registerReservation(ReservationRegisterParam param) {

        shopJpaRepository.findById(param.getShopId())
                .orElseThrow(() -> new CrudException(VALUE_NOT_FOUND, "Shop not found"));
        Reservation reservation = param.toDomain();

        reservationJpaRepository.save(reservation);
        //Redis 저장
        return ApiResponse.of("SUCCESS");
    }

    public ApiResponse<?> deleteReservation(Integer id) {
        reservationJpaRepository.deleteById(id);
        return ApiResponse.of("SUCCESS");
    }

    public Page<ReservationSearchRequest> search(Integer memberId, Pageable pageable) {
        Page<Reservation> result = reservationJpaRepository.findByMemberNo(memberId, pageable);
        return result.map(ReservationSearchRequest::new);
    }

    public ApiResponse<?> updateReservation(Integer id, ReservationUpdateParam param) {
        Reservation reservation = reservationJpaRepository.findById(id)
                .orElseThrow(() -> new CrudException(VALUE_NOT_FOUND, "Reservation not found"));
        reservation.update(param);
        return ApiResponse.of("SUCCESS");
    }
}
