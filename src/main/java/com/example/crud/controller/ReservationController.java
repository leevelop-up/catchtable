package com.example.crud.controller;

import com.example.crud.dto.reservation.ReservationRegisterParam;
import com.example.crud.dto.reservation.ReservationSearchRequest;
import com.example.crud.dto.reservation.ReservationUpdateParam;
import com.example.crud.dto.reservation.ReservationUpdateRequest;
import com.example.crud.dto.reservation.ReservationRequest;
import com.example.crud.dto.response.ApiResponse;
import com.example.crud.enums.ReturnCode;
import com.example.crud.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reservations")
    public ApiResponse<?> register(@RequestBody @Valid ReservationRequest reservationRequest) {
        ReservationRegisterParam param = reservationRequest.toParam();
        reservationService.registerReservation(param);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @DeleteMapping("/reservations/{id}")
    public ApiResponse<?> delete(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }

    @GetMapping("/reservations")
    public ApiResponse<?> search(@ModelAttribute ReservationSearchRequest reservationSearchRequest,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReservationSearchRequest> result = reservationService.search(
                reservationSearchRequest.getMemberNo(),
                pageable
        );

        return ApiResponse.of(result);
    }

    @PatchMapping("/reservations/{id}")
    public ApiResponse<?> update(@PathVariable Integer id, @RequestBody ReservationUpdateRequest reservationUpdateRequest) {
        ReservationUpdateParam param = reservationUpdateRequest.toParam();
        reservationService.updateReservation(id, param);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }
}
