package com.example.banking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable=false)
    private String accountNo;

    @NotBlank
    private String holderName;

    // BCrypt hashed PIN
    private String pinHash;

    @Column(precision = 19, scale = 2)
    private BigDecimal balance;

    public Account() {}
    public Account(Long id, String accountNo, String holderName, String pinHash, BigDecimal balance) {
        this.id = id; this.accountNo = accountNo; this.holderName = holderName; this.pinHash = pinHash; this.balance = balance;
    }
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getAccountNo(){return accountNo;} public void setAccountNo(String a){this.accountNo=a;}
    public String getHolderName(){return holderName;} public void setHolderName(String h){this.holderName=h;}
    public String getPinHash(){return pinHash;} public void setPinHash(String p){this.pinHash=p;}
    public BigDecimal getBalance(){return balance;} public void setBalance(BigDecimal b){this.balance=b;}
}
