package com.example.crud.domain;

import com.example.crud.dto.param.ShopRegisterParam;
import com.example.crud.dto.param.ShopUpdateParam;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shop")
public class Shop extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Float rating;
    private String category;
    private String city;
    private String district;
    private Integer capacity;

    @Column(name = "opentime")
    private LocalTime openTime;
    @Column(name = "closetime")
    private LocalTime closeTime;

    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", category='" + category + '\'' +
                ", city=" + city +
                ", district='" + district + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }

    public void update(ShopUpdateParam param) {
        this.name = param.getName();
        this.rating = param.getRating();
        this.category = param.getCategory();
        this.city = param.getCity();
        this.district = param.getDistrict();
        this.capacity = param.getCapacity();
        this.openTime = param.getOpenTime();
        this.closeTime = param.getCloseTime();
    }
}
