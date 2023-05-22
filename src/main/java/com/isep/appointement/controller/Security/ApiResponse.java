package com.isep.appointement.controller.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse {
    private int status;
    private String message;
    private Object result;

    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
