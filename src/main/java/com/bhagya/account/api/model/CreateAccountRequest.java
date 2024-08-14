package com.bhagya.account.api.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    private Long customerId;
    private BigDecimal initialCredit;
}
