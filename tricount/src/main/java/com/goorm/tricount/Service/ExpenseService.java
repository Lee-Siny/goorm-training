package com.goorm.tricount.Service;

import com.goorm.tricount.dto.ExpenseRequest;
import com.goorm.tricount.dto.ExpenseResult;
import com.goorm.tricount.model.Expense;
import com.goorm.tricount.model.Member;
import com.goorm.tricount.model.Settlement;
import com.goorm.tricount.repository.ExpenseRepository;
import com.goorm.tricount.repository.MemberRepository;
import com.goorm.tricount.repository.SettlementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final MemberRepository memberRepository;
    private final SettlementRepository settlementRepository;

    // 지출 추가
    public ExpenseResult addExpense(ExpenseRequest expenseRequest) {
        //예외 처리
        Optional<Member> payer = memberRepository.findById(expenseRequest.getPayerMemberId());
        if (!payer.isPresent()) {
            throw new RuntimeException("INVALID MEMBER ID ! (PAYER)");
        } //if

        // 정산 존재 여부 예외
        Optional<Settlement> settlement = settlementRepository.findById(expenseRequest.getSettlementId());
        if(!settlement.isPresent()) {
            throw new RuntimeException("INVALID SETTLEMENT ID !");
        } //if

        //지출 저장
        Expense expense = Expense.builder()
                .name(expenseRequest.getName())
                .settlementId(expenseRequest.getSettlementId())
                .payerMemberId(expenseRequest.getPayerMemberId())
                .amount(expenseRequest.getAmount())
                .expenseDateTime(Objects.nonNull(expenseRequest.getExpenseDateTime()) ? expenseRequest.getExpenseDateTime() : LocalDateTime.now())
                .build();
        expenseRepository.save(expense);
        return null;
    } //addExpense()

} //class
