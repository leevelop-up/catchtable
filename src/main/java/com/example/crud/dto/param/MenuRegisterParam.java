package com.example.crud.dto.param;

import com.example.crud.domain.Menu;
import com.example.crud.domain.Shop;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Getter
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
                ", shop_id='" + shopId + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description=" + description;
    }
}
