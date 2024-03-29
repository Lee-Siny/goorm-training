package com.goorm.tricount.controller;

import com.goorm.tricount.Service.ExpenseService;
import com.goorm.tricount.dto.ExpenseRequest;
import com.goorm.tricount.dto.ExpenseResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    // 정산 추가
    @PostMapping("/expenses/add")
    public ResponseEntity<ExpenseResult> addExpenseToSettlement(
            @Valid @RequestBody ExpenseRequest expenseRequest) {
        return new ResponseEntity<>(expenseService.addExpense(expenseRequest), HttpStatus.OK);
    } //addExpenseToSettlement
} //class
