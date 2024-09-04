package com.commerce.dto;


import lombok.*;


import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class ProductEs {
    private Integer id;
    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price;
}
