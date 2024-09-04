package com.commerce.kafka;

import com.commerce.customer.CustomerResponse;
import com.commerce.order.PaymentMethod;
import com.commerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
