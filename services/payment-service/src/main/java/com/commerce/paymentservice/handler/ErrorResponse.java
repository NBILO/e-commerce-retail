package com.commerce.paymentservice.handler;

import java.util.Map;

public record ErrorResponse(
    Map<String, String> errors
) {

}
