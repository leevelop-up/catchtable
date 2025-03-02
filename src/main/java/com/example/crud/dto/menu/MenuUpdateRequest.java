package com.example.crud.dto.menu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuUpdateRequest {
    private String name;
    private long price;
    private String description;

    public MenuUpdateParam toParam() {
        return MenuUpdateParam.builder()
                .name(name)
                .price(price)
                .description(description)
                .build();
    }
}
