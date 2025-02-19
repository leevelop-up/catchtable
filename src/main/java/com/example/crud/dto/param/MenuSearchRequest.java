package com.example.crud.dto.param;

import com.example.crud.domain.Menu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuSearchRequest {


    private Integer shopId;
    private String name;
    private long price;
    private String description;

    public static MenuSearchRequest from(Menu menu) {
        return MenuSearchRequest.builder()
                .shopId(menu.getShop() != null ? menu.getShop().getId() : null) // ✅ shop_id 포함
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .build();
    }
}

