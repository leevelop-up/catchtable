package com.example.crud.dto.param;

import com.example.crud.domain.Shop;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopUpdateParam {
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
    private LocalTime openTime;
    @NotNull
    private LocalTime closeTime;

    public Shop toDomain() {
        return Shop.builder()
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
