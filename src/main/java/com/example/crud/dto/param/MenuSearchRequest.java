package com.example.crud.dto.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MenuSearchRequest {

    @NotBlank
    private String name;
    @NotNull
    private long price;
    @NotBlank
    private String description;

}
