package com.example.crud.dto.param;

import com.example.crud.domain.Reservation;
import com.example.crud.domain.Shop;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRegisterParam {

        private Integer memberNo;
        private Integer shopId;
        private LocalDate date;
        private String time;
        private Integer status;
        private Integer peopleCount;

        public String toString() {
            return "Reservation{" +
                    ", member_id='" + memberNo + '\'' +
                    ", shop_id='" + shopId + '\'' +
                    ", date='" + date + '\'' +
                    ", time='" + time + '\'' +
                    ", status='" + status + '\'' +
                    ", people_count='" + peopleCount + '\'' +
                    '}';
        }

        public Reservation toDomain() {
            return Reservation.builder()
                    .memberNo(memberNo)
                    .shopId(shopId)
                    .date(date)
                    .time(time)
                    .status(status)
                    .peopleCount(peopleCount)
                    .build();
        }
}
