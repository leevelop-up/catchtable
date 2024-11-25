package com.example.crud.controller;

import com.example.crud.dto.menu.MenuRequest;
import com.example.crud.dto.param.MenuRegisterParam;
import com.example.crud.dto.response.ApiResponse;
import com.example.crud.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuController {

    private final MenuService menuService;
    @PostMapping("/menus")
    public ApiResponse<?> register(@RequestBody @Valid MenuRequest menuRequest) {
        MenuRegisterParam param = menuRequest.toParam();
        menuService.registerMenu(param);
        return ApiResponse.of("SUCCESS");
    }
}
