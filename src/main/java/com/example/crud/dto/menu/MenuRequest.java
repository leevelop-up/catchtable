package com.example.crud.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequest {
    private Integer shopId;
    private String name;
    private long price;
    private String description;

    public MenuRegisterParam toParam() {
        return MenuRegisterParam.builder()
                .shopId(shopId)
                .name(name)
                .price(price)
                .description(description)
                .build();
    }
    public String toString() {
        return "Member{" +
                ", shop_id='" + shopId + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description=" + description;
    }
}
