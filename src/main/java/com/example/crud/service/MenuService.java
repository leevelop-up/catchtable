package com.example.crud.service;

import com.example.crud.domain.Menu;
import com.example.crud.dto.menu.MenuRegisterParam;
import com.example.crud.dto.menu.MenuSearchRequest;
import com.example.crud.dto.menu.MenuUpdateParam;
import com.example.crud.dto.response.ApiResponse;
import com.example.crud.exception.CrudException;
import com.example.crud.repository.MenuRepository;
import com.example.crud.repository.ShopJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        System.out.println(param.getShopId());
        shopRepository.findById(param.getShopId())
                .orElseThrow(() -> new CrudException(VALUE_NOT_FOUND, "Shop not found"));
        Menu menu = param.toDomain();
        menuRepository.save(menu);
        return ApiResponse.of("SUCCESS");
    }

    public ApiResponse<?> deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new CrudException(VALUE_NOT_FOUND, "Menu not found"));
        menuRepository.delete(menu);
        return ApiResponse.of("SUCCESS");
    }
    public ApiResponse<?> updateMenu(Long id, MenuUpdateParam param) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new CrudException(VALUE_NOT_FOUND, "Menu not found"));
        menu.update(param);
        return ApiResponse.of("SUCCESS");
    }

    public Page<MenuSearchRequest> search(Integer shopId, String name, long price, String description, Pageable pageable) {
        Page<Menu> result = menuRepository.search(shopId, name, price, description, pageable);
        return result.map(MenuSearchRequest::from);
    }
}
