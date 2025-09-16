package com.auction.identity.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String error;
    private String message;

    public ErrorResponse() {}
}

