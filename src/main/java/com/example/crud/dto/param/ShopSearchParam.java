package com.example.crud.dto.param;

import com.example.crud.enums.Category;
import lombok.Data;

@Data
public class ShopSearchParam {
    private String name;
    private String city;
    private Category category;
    private String district;

}
