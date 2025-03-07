package com.example.crud.exception;

public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    MEMBER_DUPLICATION(400, "C002", "Member Duplication"),
    METHOD_NOT_ALLOWED(405, "C003", "Invalid Input Value"),
    VALUE_NOT_FOUND(404, "C005", "Value Not Found"),
    DELETE_FAILED(406, "C005", "DELETE_FAILED"),
    SEAT_IS_FULL(401, "C004", "Seat is full"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error");

    public final String code;
    public final String message;
    public final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
