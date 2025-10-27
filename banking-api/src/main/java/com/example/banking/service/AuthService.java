package com.example.banking.service;

import com.example.banking.dto.AuthRequest;
import com.example.banking.dto.AuthResponse;
import com.example.banking.model.Account;
import com.example.banking.repo.AccountRepository;
import com.example.banking.security.JwtService;
import com.example.banking.exception.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    private final AccountRepository repo;
    private final BCryptPasswordEncoder enc;
    private final JwtService jwt;

    public AuthService(AccountRepository repo, BCryptPasswordEncoder enc, JwtService jwt) {
        this.repo = repo; this.enc = enc; this.jwt = jwt;
    }

    public AuthResponse login(AuthRequest req){
        Account acc = repo.findByAccountNo(req.getAccountNo())
                .orElseThrow(()->new NotFoundException("Account not found"));
        if(!enc.matches(req.getPin(), acc.getPinHash())){
            throw new NotFoundException("Invalid account/pin");
        }
        String token = jwt.generate(acc.getAccountNo(), Map.of("name", acc.getHolderName()));
        return new AuthResponse(token, acc.getAccountNo(), acc.getHolderName());
    }
}
