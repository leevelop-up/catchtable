package com.example.crud.dto.reservation;

import com.example.crud.dto.param.ReservationUpdateParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationUpdateRequest {
    private LocalDate date;
    private String time;
    private Integer status;
    private Integer peopleCount;

    public ReservationUpdateParam toParam() {
        return ReservationUpdateParam.builder()
                .date(date)
                .time(time)
                .status(status)
                .peopleCount(peopleCount)
                .build();
    }
}
