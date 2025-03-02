package com.example.crud.domain;

import com.example.crud.dto.reservation.ReservationUpdateParam;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservation")
public class Reservation extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer memberNo;
    private Integer shopId;
    private LocalDate date;
    private String time;
    private Integer status;
    private Integer peopleCount;

    public void update(ReservationUpdateParam param) {
        this.date = param.getDate();
        this.time = param.getTime();
        this.status = param.getStatus();
        this.peopleCount = param.getPeopleCount();
    }
}
