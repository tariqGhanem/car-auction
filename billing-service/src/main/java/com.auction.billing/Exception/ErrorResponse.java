package com.auction.billing.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String error;
    private Object message;

    public ErrorResponse() {}
}

