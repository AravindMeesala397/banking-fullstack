package com.example.banking.service;

import com.example.banking.dto.AccountResponse;
import com.example.banking.dto.TransferRequest;
import com.example.banking.exception.*;
import com.example.banking.model.Account;
import com.example.banking.model.Transaction;
import com.example.banking.repo.AccountRepository;
import com.example.banking.repo.TransactionRepository;
import com.example.banking.util.TransactionType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accounts;
    private final TransactionRepository txRepo;

    public AccountService(AccountRepository accounts, TransactionRepository txRepo) {
        this.accounts = accounts;
        this.txRepo = txRepo;
    }

    public Account getByAccountNo(String accNo) {
        return accounts.findByAccountNo(accNo)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public AccountResponse summary(String accNo) {
        var acc = getByAccountNo(accNo);
        return new AccountResponse(acc.getAccountNo(), acc.getHolderName(), acc.getBalance());
    }

    public List<Transaction> statement(String accNo) {
        getByAccountNo(accNo); // ensure exists
        return txRepo.findByAccountNoOrderByCreatedAtDesc(accNo);
    }

    @Transactional
    public AccountResponse deposit(String accNo, BigDecimal amount) {
        if (amount.signum() <= 0) throw new BadRequestException("Amount must be positive");
        var acc = getByAccountNo(accNo);
        acc.setBalance(acc.getBalance().add(amount));
        accounts.save(acc);
        txRepo.save(new Transaction(accNo, TransactionType.DEPOSIT, amount, acc.getBalance(), "Deposit"));
        return new AccountResponse(acc.getAccountNo(), acc.getHolderName(), acc.getBalance());
    }

    @Transactional
    public AccountResponse withdraw(String accNo, BigDecimal amount) {
        if (amount.signum() <= 0) throw new BadRequestException("Amount must be positive");
        var acc = getByAccountNo(accNo);
        if (acc.getBalance().compareTo(amount) < 0)
            throw new InsufficientFundsException("Insufficient balance");
        acc.setBalance(acc.getBalance().subtract(amount));
        accounts.save(acc);
        txRepo.save(new Transaction(accNo, TransactionType.WITHDRAW, amount, acc.getBalance(), "Withdraw"));
        return new AccountResponse(acc.getAccountNo(), acc.getHolderName(), acc.getBalance());
    }

    @Transactional
    public AccountResponse transfer(String fromAccNo, TransferRequest req) {
        if (fromAccNo.equals(req.getToAccountNo()))
            throw new BadRequestException("Cannot transfer to same account");
        if (req.getAmount().signum() <= 0)
            throw new BadRequestException("Amount must be positive");

        var from = getByAccountNo(fromAccNo);
        var to = getByAccountNo(req.getToAccountNo());

        if (from.getBalance().compareTo(req.getAmount()) < 0)
            throw new InsufficientFundsException("Insufficient balance");

        from.setBalance(from.getBalance().subtract(req.getAmount()));
        to.setBalance(to.getBalance().add(req.getAmount()));
        accounts.save(from);
        accounts.save(to);

        txRepo.save(new Transaction(from.getAccountNo(), TransactionType.TRANSFER_OUT,
                req.getAmount(), from.getBalance(), "Transfer to " + to.getAccountNo()));
        txRepo.save(new Transaction(to.getAccountNo(), TransactionType.TRANSFER_IN,
                req.getAmount(), to.getBalance(), "Transfer from " + from.getAccountNo()));

        return new AccountResponse(from.getAccountNo(), from.getHolderName(), from.getBalance());
    }

    // 🆕 Change PIN logic
    @Transactional
    public void changePin(String accNo, String oldPin, String newPin, BCryptPasswordEncoder encoder) {
        Account acc = getByAccountNo(accNo);
        if (!encoder.matches(oldPin, acc.getPinHash())) {
            throw new BadRequestException("Old PIN incorrect");
        }
        acc.setPinHash(encoder.encode(newPin));
        accounts.save(acc);
    }
}
