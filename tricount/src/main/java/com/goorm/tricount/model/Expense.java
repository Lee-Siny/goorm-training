package com.goorm.tricount.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    // [지출]에 필요한 요소
    // 지출 id (DB 저장 및 API 구성시 필요)
    // 지출 이름
    // 지출한 사람 - 어떤 유저가 지출을 했는지 매핑이 필요합니다.
    // 지출 금액
    // 지출 날짜
    private Long id;
    private String name;
    private Long settlementId; //정산은 여러개의 지출을 가질 수 있다
    private Long payerMemberId; //어떤 유저의 지출인지
    private BigDecimal amount;
    private LocalDateTime expenseDateTime;
} //class
