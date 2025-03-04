package com.example.crud.dto.shop;

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
public class ShopUpdateRequest {
    @NotBlank
    private String name;
    @NotNull
    private Float rating;
    @NotBlank
    private Category category;
    @NotBlank
    private String city;
    private Integer seat;
    @NotBlank
    private String district;
    @NotNull
    private Integer capacity;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openTime;
    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closeTime;

    public ShopUpdateParam toParam() {
        return ShopUpdateParam.builder()
                .name(name)
                .rating(rating)
                .category(category)
                .city(city)
                .district(district)
                .capacity(capacity)
                .seat(seat)
                .openTime(openTime)
                .closeTime(closeTime)
                .build();
    }
}
