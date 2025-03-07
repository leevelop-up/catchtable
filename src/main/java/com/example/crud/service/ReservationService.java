package com.example.crud.service;

import com.example.crud.domain.Reservation;
import com.example.crud.domain.Shop;
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

        /*
         1. 해당 장소가 있는지 확인
         2. 장소의 좌석 여부 화가인
         3. 장소에 좌석이 남아있으면 예약 및 큐에 저장
         4. 큐에 저장된 데이터 기준 db에 저장
         */
        findShopById(param.getShopId());

        Reservation reservation = param.toDomain();

        reservationJpaRepository.save(reservation);
        //Redis 저장
        return ApiResponse.of("SUCCESS");
    }
    public void findShopById(Integer id) {
        shopJpaRepository.findById(id)
                .orElseThrow(() -> new CrudException(VALUE_NOT_FOUND, "Shop not found"));
    }
    public void seatCheck(Shop shop, Reservation reservation) {
        if (shop.getSeat() < reservation.getSeat()) {
            throw new CrudException(VALUE_NOT_FOUND, "Seat is not enough");
        }
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
