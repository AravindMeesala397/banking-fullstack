package com.example.banking.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferRequest {
    @NotBlank private String toAccountNo;
    @NotNull @DecimalMin("0.01") private BigDecimal amount;
    public String getToAccountNo(){return toAccountNo;} public void setToAccountNo(String t){this.toAccountNo=t;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal a){this.amount=a;}
}
