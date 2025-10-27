package com.example.banking.controller;

import com.example.banking.dto.*;
import com.example.banking.model.Transaction;
import com.example.banking.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    private String currentAcc(HttpServletRequest req) {
        return (String) req.getUserPrincipal().getName();
    }

    @GetMapping("/me")
    public ResponseEntity<AccountResponse> me(HttpServletRequest req) {
        return ResponseEntity.ok(service.summary(currentAcc(req)));
    }

    @GetMapping("/me/transactions")
    public ResponseEntity<List<Transaction>> myTx(HttpServletRequest req) {
        return ResponseEntity.ok(service.statement(currentAcc(req)));
    }

    @PostMapping("/me/deposit")
    public ResponseEntity<AccountResponse> deposit(HttpServletRequest req, @Valid @RequestBody AmountRequest body) {
        return ResponseEntity.ok(service.deposit(currentAcc(req), body.getAmount()));
    }

    @PostMapping("/me/withdraw")
    public ResponseEntity<AccountResponse> withdraw(HttpServletRequest req, @Valid @RequestBody AmountRequest body) {
        return ResponseEntity.ok(service.withdraw(currentAcc(req), body.getAmount()));
    }

    @PostMapping("/me/transfer")
    public ResponseEntity<AccountResponse> transfer(HttpServletRequest req, @Valid @RequestBody TransferRequest body) {
        return ResponseEntity.ok(service.transfer(currentAcc(req), body));
    }

    // 🆕 Change PIN endpoint
    @PostMapping("/me/change-pin")
    public ResponseEntity<?> changePin(
            HttpServletRequest req,
            @Valid @RequestBody ChangePinRequest body,
            BCryptPasswordEncoder encoder
    ) {
        service.changePin(currentAcc(req), body.getOldPin(), body.getNewPin(), encoder);
        return ResponseEntity.ok(Map.of("message", "PIN updated successfully"));
    }
}
