package com.example.crud.dto.param;

import com.example.crud.domain.Menu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class MenuSearchRequest {


    private String name;

    private long price;

    private String description;

}
