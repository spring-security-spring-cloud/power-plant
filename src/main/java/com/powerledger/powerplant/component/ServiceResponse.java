package com.powerledger.powerplant.component;

import lombok.Data;

@Data
public class ServiceResponse {
    private Object data;
    private String message;
    //private int statusCode;

    public ServiceResponse(String message, Object data) {
        //this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
