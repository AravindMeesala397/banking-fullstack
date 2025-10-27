package com.example.banking;

import com.example.banking.model.Account;
import com.example.banking.repo.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;

@SpringBootApplication
public class BankingApplication {
    public static void main(String[] args) { SpringApplication.run(BankingApplication.class, args); }

    @Bean BCryptPasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

    // Seed a couple of accounts if not present
    @Bean CommandLineRunner seed(AccountRepository repo, BCryptPasswordEncoder enc){
        return args -> {
            if(repo.findByAccountNo("ACC1001").isEmpty()){
                repo.save(new Account(null,"ACC1001","Aravind", enc.encode("1234"), new BigDecimal("10000.00")));
            }
            if(repo.findByAccountNo("ACC1002").isEmpty()){
                repo.save(new Account(null,"ACC1002","Sita", enc.encode("4321"), new BigDecimal("7500.00")));
            }
        };
    }
}
