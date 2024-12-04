package com.example.crud.service;

import com.example.crud.domain.Shop;
import com.example.crud.dto.param.ShopRegisterParam;
import com.example.crud.dto.param.ShopSearchParam;
import com.example.crud.dto.param.ShopUpdateParam;
import com.example.crud.enums.Category;
import com.example.crud.exception.CrudException;
import com.example.crud.repository.RedisRepository;
import com.example.crud.repository.ShopJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.crud.exception.ErrorCode.VALUE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {
    private final ShopJpaRepository shopJpaRepository;
    private final RedisRepository redisRepository;
    public void registerShop(ShopRegisterParam shopRegisterParam) {
        Shop shop = shopRegisterParam.toDomain();
        shopJpaRepository.save(shop);

    }

    public void updateShop(Integer id, ShopUpdateParam param) {
        Shop shop = shopJpaRepository.findById(id)
                .orElseThrow(() -> {throw new CrudException(VALUE_NOT_FOUND, "Shop not found");});
        shop.update(param);
    }

    public Page<Shop> searchShop(String name, String city, String district, Category category, Pageable pageable){
        Page<Shop> result;
        result = shopJpaRepository.search(name, city, district,category, pageable);
        return result;
    }


    public void deleteShop(Integer id) {
        Shop shop = shopJpaRepository.findById(id)
                .orElseThrow(() -> {throw new CrudException(VALUE_NOT_FOUND, "Shop not found");});
        shopJpaRepository.delete(shop);
    }

    public boolean isShopListRedis() {
        Set<String> size = redisRepository.hashSize("shop");
        System.out.println(size);
        return !size.isEmpty();
    }

    public void populateRedisFromDatabase() {
        List<Shop> shops = shopJpaRepository.findAll();

        // Shop 데이터를 Redis에 저장
        for (Shop shop : shops) {
            redisRepository.hashPutAll("shop:" + shop.getId(), Map.of(
                    "name", shop.getName(),
                    "city", shop.getCity(),
                    "district", shop.getDistrict(),
                    "category", shop.getCategory().name()
            ));
        }
    }
    // Redis에서 Shop 데이터 검색 및 페이징
    public Page<Map<String, String>> searchShopFromRedis(String name, String city, String district, Category category, Pageable pageable) {
        List<String> keys = redisRepository.getAllKeys("shop:*");

        List<Map<String, String>> shops = keys.stream()
                .map(redisRepository::getHashEntries)
                .filter(shop -> {
                    if (name != null && !shop.get("name").contains(name)) return false;
                    if (city != null && !shop.get("city").equals(city)) return false;
                    if (district != null && !shop.get("district").equals(district)) return false;
                    if (category != null && !shop.get("category").equals(category)) return false;
                    return true;
                })
                .collect(Collectors.toList());
        // 페이징 처리 전에 범위 검증
        int totalElements = shops.size();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), totalElements);

        if (start >= totalElements) {
            // 요청된 페이지가 데이터 범위를 벗어남
            return new PageImpl<>(Collections.emptyList(), pageable, totalElements);
        }

        List<Map<String, String>> pagedShops = shops.subList(start, end);

        return new PageImpl<>(pagedShops, pageable, totalElements);
    }
}
