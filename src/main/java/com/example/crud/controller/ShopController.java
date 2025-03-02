package com.example.crud.controller;

import com.example.crud.dto.shop.ShopRegisterParam;
import com.example.crud.dto.shop.ShopSearchParam;
import com.example.crud.dto.shop.ShopUpdateParam;
import com.example.crud.dto.response.ApiResponse;
import com.example.crud.dto.shop.ShopRegisterRequest;
import com.example.crud.dto.shop.ShopUpdateRequest;
import com.example.crud.enums.ReturnCode;
import com.example.crud.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShopController {
    private final ShopService shopService;

    @PostMapping("/shops")
    public ApiResponse<?> register(@RequestBody @Valid ShopRegisterRequest shopRegisterRequest) {
        ShopRegisterParam param = shopRegisterRequest.toParam();
        shopService.registerShop(param);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @DeleteMapping("/shops/{id}")
    public ApiResponse<?> delete(@PathVariable Integer id) {
        shopService.deleteShop(id);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @GetMapping("/shops")
    public ApiResponse<?> search(@ModelAttribute ShopSearchParam shopSearchParam, @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        boolean isRedisPopulated = shopService.isShopListRedis();
        System.out.println(isRedisPopulated);
        // 2. Redis에 데이터가 없으면 데이터베이스에서 Shop 정보 가져와 Redis에 저장
        if (!isRedisPopulated) {
            shopService.populateRedisFromDatabase();
        }

        return ApiResponse.of(shopService.searchShopFromRedis(shopSearchParam.getName(),shopSearchParam.getCity(),shopSearchParam.getDistrict(),shopSearchParam.getCategory(), pageable));
    }

    @PatchMapping("/shops/{id}")
    public ApiResponse<?> update(@PathVariable Integer id, @RequestBody ShopUpdateRequest shopUpdateRequest) {
        ShopUpdateParam param = shopUpdateRequest.toParam();
        shopService.updateShop(id, param);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }


}
