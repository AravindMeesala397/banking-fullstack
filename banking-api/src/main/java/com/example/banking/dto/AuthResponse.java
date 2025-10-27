package com.example.banking.dto;

public class AuthResponse {
    private String token;
    private String accountNo;
    private String holderName;
    public AuthResponse(String token, String accountNo, String holderName){
        this.token=token; this.accountNo=accountNo; this.holderName=holderName;
    }
    public String getToken(){return token;}
    public String getAccountNo(){return accountNo;}
    public String getHolderName(){return holderName;}
}
