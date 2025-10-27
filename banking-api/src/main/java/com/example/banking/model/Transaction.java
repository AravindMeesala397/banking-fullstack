package com.example.banking.model;

import com.example.banking.util.TransactionType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNo;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal postBalance;
    private String note;
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Transaction() {}
    public Transaction(String accountNo, TransactionType type, BigDecimal amount, BigDecimal postBalance, String note) {
        this.accountNo = accountNo; this.type = type; this.amount = amount; this.postBalance = postBalance; this.note = note;
    }
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getAccountNo(){return accountNo;} public void setAccountNo(String a){this.accountNo=a;}
    public TransactionType getType(){return type;} public void setType(TransactionType t){this.type=t;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal a){this.amount=a;}
    public BigDecimal getPostBalance(){return postBalance;} public void setPostBalance(BigDecimal p){this.postBalance=p;}
    public String getNote(){return note;} public void setNote(String n){this.note=n;}
    public OffsetDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(OffsetDateTime c){this.createdAt=c;}
}
