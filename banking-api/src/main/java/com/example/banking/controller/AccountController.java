package com.example.banking.controller;

import com.example.banking.dto.*;
import com.example.banking.model.Transaction;
import com.example.banking.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService service;
    public AccountController(AccountService service){ this.service = service; }

    private String currentAcc(HttpServletRequest req){
        // subject set in JwtAuthFilter
        return (String) req.getUserPrincipal().getName();
    }

    @GetMapping("/me")
    public ResponseEntity<AccountResponse> me(HttpServletRequest req){
        return ResponseEntity.ok(service.summary(currentAcc(req)));
    }

    @GetMapping("/me/transactions")
    public ResponseEntity<List<Transaction>> myTx(HttpServletRequest req){
        return ResponseEntity.ok(service.statement(currentAcc(req)));
    }

    @PostMapping("/me/deposit")
    public ResponseEntity<AccountResponse> deposit(HttpServletRequest req, @Valid @RequestBody AmountRequest body){
        return ResponseEntity.ok(service.deposit(currentAcc(req), body.getAmount()));
    }

    @PostMapping("/me/withdraw")
    public ResponseEntity<AccountResponse> withdraw(HttpServletRequest req, @Valid @RequestBody AmountRequest body){
        return ResponseEntity.ok(service.withdraw(currentAcc(req), body.getAmount()));
    }

    @PostMapping("/me/transfer")
    public ResponseEntity<AccountResponse> transfer(HttpServletRequest req, @Valid @RequestBody TransferRequest body){
        return ResponseEntity.ok(service.transfer(currentAcc(req), body));
    }
}
