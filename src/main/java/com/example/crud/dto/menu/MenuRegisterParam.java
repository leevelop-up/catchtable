package com.example.crud.dto.menu;

import com.example.crud.domain.Menu;
import com.example.crud.domain.Shop;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRegisterParam {

    private Integer shopId;
    private String name;
    private long price;
    private String description;

    public Menu toDomain() {
        return Menu.builder()
                .shop(shopId == null ? null : Shop.builder().id(shopId).build())
                .name(name)
                .price(price)
                .description(description)
                .build();
    }
    public String toString() {
        return "Member{" +
                ", shopid='" + shopId + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description=" + description;
    }
}
