package com.example.crud.dto.shop;

import com.example.crud.dto.param.ShopRegisterParam;
import com.example.crud.dto.param.ShopUpdateParam;
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
    private String category;
    @NotBlank
    private String city;
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
                .openTime(openTime)
                .closeTime(closeTime)
                .build();
    }
}
