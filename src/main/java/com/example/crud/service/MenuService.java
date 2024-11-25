package com.example.crud.service;

import com.example.crud.domain.Menu;
import com.example.crud.domain.Shop;
import com.example.crud.dto.param.MenuRegisterParam;
import com.example.crud.dto.response.ApiResponse;
import com.example.crud.exception.CrudException;
import com.example.crud.repository.MenuRepository;
import com.example.crud.repository.ShopJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.crud.exception.ErrorCode.VALUE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {
    private final MenuRepository menuRepository;
    private final ShopJpaRepository shopRepository;

    public ApiResponse<?> registerMenu(MenuRegisterParam param) {
        shopRepository.findById(param.getShopId())
                .orElseThrow(() -> new CrudException(VALUE_NOT_FOUND, "Shop not found"));
        Menu menu = param.toDomain();
        menuRepository.save(menu);
        return ApiResponse.of("SUCCESS");
    }
}
