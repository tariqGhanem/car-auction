package com.auction.auction.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String error;
    private Object message;

    public ErrorResponse() {}
}

