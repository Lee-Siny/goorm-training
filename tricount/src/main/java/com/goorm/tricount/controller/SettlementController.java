package com.goorm.tricount.controller;

import com.goorm.tricount.Service.SettlementService;
import com.goorm.tricount.dto.BalanceResult;
import com.goorm.tricount.model.Settlement;
import com.goorm.tricount.util.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SettlementController {
    private final SettlementService settlementService;

    // 정산 생성
    @PostMapping("/settles/create")
    public ResponseEntity<Settlement> createSettlement(@RequestParam String settlementName) {
        return new ResponseEntity<>(settlementService.createAndJoinSettlement(settlementName, MemberContext.getCurrentMember()), HttpStatus.OK);
    } //createSettlement

    // 정산 참여
    @PostMapping("/settles/{id}/join")
    public ResponseEntity<Void> joinSettlement(@PathVariable("id") Long settlementId) {
        settlementService.joinSettlement(settlementId, MemberContext.getCurrentMember().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    } //joinSettlement

    // 정산 결과
    @GetMapping("/settles/{id}/balance")
    public ResponseEntity<List<BalanceResult>> getSettlementBalanceResult(@PathVariable("id") Long settlmentId) {
        return new ResponseEntity<>(settlementService.getBalanceResult(settlmentId), HttpStatus.OK);
    } //getSettlementBalanceResult
} //class
