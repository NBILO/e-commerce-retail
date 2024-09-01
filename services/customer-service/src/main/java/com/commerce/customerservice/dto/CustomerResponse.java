package com.commerce.customerservice.dto;

import com.commerce.customerservice.domain.Address;

public record CustomerResponse(
    String id,
    String firstname,
    String lastname,
    String email,
    Address address
) {

}
