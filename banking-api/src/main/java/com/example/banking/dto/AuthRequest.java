package com.example.banking.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {
    @NotBlank private String accountNo;
    @NotBlank private String pin;
    public String getAccountNo(){return accountNo;} public void setAccountNo(String a){this.accountNo=a;}
    public String getPin(){return pin;} public void setPin(String p){this.pin=p;}
}
