package com.example.crud.dto.reservation;

import com.example.crud.dto.param.ReservationRegisterParam;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationRequest {
    private Integer memberNo;
    private Integer shopId;
    private LocalDate date;
    private String time;
    private Integer status;
    private Integer peopleCount;

    public ReservationRegisterParam toParam() {
        return ReservationRegisterParam.builder()
                .memberNo(memberNo)
                .shopId(shopId)
                .date(date)
                .time(time)
                .status(status)
                .peopleCount(peopleCount)
                .build();
    }
}
