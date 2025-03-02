package com.example.crud.dto.menu;

import com.example.crud.domain.Menu;
import com.example.crud.domain.Shop;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MenuUpdateParam {

    @NotBlank
    private String name;
    @NotNull
    private long price;
    @NotBlank
    private String description;

}
