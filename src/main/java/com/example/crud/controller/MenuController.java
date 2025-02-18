package com.example.crud.controller;

import com.example.crud.domain.Menu;
import com.example.crud.dto.menu.MenuRequest;
import com.example.crud.dto.menu.MenuUpdateRequest;
import com.example.crud.dto.param.MenuRegisterParam;
import com.example.crud.dto.param.MenuSearchRequest;
import com.example.crud.dto.param.MenuUpdateParam;

import com.example.crud.dto.response.ApiResponse;
import com.example.crud.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuController {

    private final MenuService menuService;
    @PostMapping("/menus")
    public ApiResponse<?> register(@RequestBody @Valid MenuRequest menuRequest) {
        MenuRegisterParam param = menuRequest.toParam();
        System.out.println(param);
        menuService.registerMenu(param);
        return ApiResponse.of("SUCCESS");
    }
    @GetMapping("/menus")
    public ApiResponse<?>  search(@ModelAttribute MenuSearchRequest menuSearchRequest, @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ApiResponse.of(menuService.search(menuSearchRequest.getName(),menuSearchRequest.getPrice(),menuSearchRequest.getDescription(),pageable));
    }
    @DeleteMapping("/menus/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ApiResponse.of("SUCCESS");
    }
    @PatchMapping("/menus/{id}")
    public ApiResponse<?> update(@PathVariable Long id, @RequestBody @Valid MenuUpdateRequest menuRequest) {
        MenuUpdateParam param = menuRequest.toParam();
        menuService.updateMenu(id, param);
        return ApiResponse.of("SUCCESS");
    }
}
