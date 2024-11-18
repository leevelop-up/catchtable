package com.example.crud.dto.shop;

import com.example.crud.dto.param.ShopRegisterParam;
import com.example.crud.enums.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopRegisterRequest {
    @NotBlank
    private String name;
    @NotNull
    private Float rating;
    @NotNull
    private Category category;
    @NotBlank
    private String city;
    @NotBlank
    private String district;
    @NotNull
    private Integer capacity;
    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull
    private LocalTime openTime;
    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull
    private LocalTime closeTime;

    public ShopRegisterParam toParam() {
        return ShopRegisterParam.builder()
                .name(name)
                .rating(rating)
                .category(category)
                .city(city)
                .district(district)
                .capacity(capacity)
                .openTime(openTime)
                .closeTime(closeTime)
                .build();
    }
    public String toString() {
        return "Shop{" +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", category='" + category + '\'' +
                ", city=" + city +
                ", district='" + district + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
