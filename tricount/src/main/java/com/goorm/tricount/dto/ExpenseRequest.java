package com.goorm.tricount.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseRequest {
    private String name;
    private Long settlementId;
    private Long payerMemberId;
    private BigDecimal amount;
    private LocalDateTime expenseDateTime;
} //class