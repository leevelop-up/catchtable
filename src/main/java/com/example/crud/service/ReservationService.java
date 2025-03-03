package com.example.crud.service;

import com.example.crud.component.DistributeLockExecutor;
import com.example.crud.domain.Reservation;
import com.example.crud.domain.Shop;
import com.example.crud.dto.reservation.ReservationRegisterParam;
import com.example.crud.dto.reservation.ReservationUpdateParam;
import com.example.crud.dto.reservation.ReservationSearchRequest;
import com.example.crud.dto.response.ApiResponse;
import com.example.crud.exception.CrudException;
import com.example.crud.repository.RedisRepository;
import com.example.crud.repository.ReservationJpaRepository;
import com.example.crud.repository.ShopJpaRepository;
import com.example.crud.util.RedisKeyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.crud.exception.ErrorCode.SEAT_IS_FULL;
import static com.example.crud.exception.ErrorCode.VALUE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationJpaRepository reservationJpaRepository;
    private final DistributeLockExecutor distributeLockExecutor;
    private final ShopJpaRepository shopJpaRepository;
    private final RedisRepository redisRepository;
    private final RedisKeyUtil redisKeyUtil;
    public ApiResponse<?> registerReservation(ReservationRegisterParam param) {

        shopCheck(param.getShopId());
        seatCheck(shopJpaRepository.findById(param.getShopId()).get(), param.getDate().toString(), param.getTime());
        String Key = redisKeyUtil.getReservationKey(param.getShopId(), param.getDate().toString(), param.getTime());
        distributeLockExecutor.execute("reservation_lock"+Key,10000,10000,()->{
            Reservation reservation = param.toDomain();
            redisRepository.addReservation(param.getShopId(), param.getDate().toString(), param.getTime(), param.getMemberNo());

            reservationJpaRepository.save(reservation);
        });
        //Redis 저장
        return ApiResponse.of("SUCCESS");
    }
    public void shopCheck(Integer shopId) {
        shopJpaRepository.findById(shopId)
                .orElseThrow(() -> new CrudException(VALUE_NOT_FOUND, "Shop not found"));
    }
    public void seatCheck(Shop shop, String date, String time) {
        // Redis에서 현재 예약 개수 조회
        Integer count = redisRepository.getReservationCount(shop.getId(), date, time);
        // 현재 예약된 좌석 개수 (없으면 0으로 처리)
        int reservationCount = (count == null) ? 0 : count;
        System.out.println("카운트:"+count);
        // 좌석 초과 여부 체크
        if (shop.getSeat() <= reservationCount) {
            throw new CrudException(SEAT_IS_FULL, "Seat is full");
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
