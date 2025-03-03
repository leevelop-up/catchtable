package com.example.crud.domain;

import com.example.crud.dto.shop.ShopUpdateParam;
import com.example.crud.enums.Category;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Float rating;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Category category;
    private String city;
    private Integer seat;
    private String district;
    private Integer capacity;

    @Column(name = "opening_time")
    private LocalTime openTime;
    @Column(name = "closing_time")
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
        this.seat = param.getSeat();
        this.district = param.getDistrict();
        this.capacity = param.getCapacity();
        this.openTime = param.getOpenTime();
        this.closeTime = param.getCloseTime();
    }
}
