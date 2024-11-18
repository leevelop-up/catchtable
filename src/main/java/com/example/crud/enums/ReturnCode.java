package com.example.crud.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnCode {
    SUCCESS("200", "Success"),
    NOT_FOUND_ENTITY("4001", "Not found the entity");

    private String returnCode;
    private String returnMessage;
}
