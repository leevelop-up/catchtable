package com.example.crud.dto.reservation;

import com.example.crud.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationSearchRequest {
    private Integer memberNo;
    private Integer shopId;
    private LocalDate date;
    private String time;

    public ReservationSearchRequest(Reservation reservation) {
        this.memberNo = reservation.getMemberNo();
        this.shopId = reservation.getShopId();
        this.date = reservation.getDate();
        this.time = reservation.getTime();
    }
}
