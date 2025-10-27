package com.example.banking.dto;

import java.math.BigDecimal;

public class AccountResponse {
    private String accountNo;
    private String holderName;
    private BigDecimal balance;

    public AccountResponse(String accountNo, String holderName, BigDecimal balance){
        this.accountNo=accountNo; this.holderName=holderName; this.balance=balance;
    }
    public String getAccountNo(){return accountNo;}
    public String getHolderName(){return holderName;}
    public BigDecimal getBalance(){return balance;}
}
