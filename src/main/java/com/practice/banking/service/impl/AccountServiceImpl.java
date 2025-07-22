package com.practice.banking.service.impl;

import com.practice.banking.dto.AccountDto;
import com.practice.banking.entity.Account;
import com.practice.banking.mapper.AccountMapper;
import com.practice.banking.repository.AccountRepository;
import com.practice.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        validateAccountBalance(accountDto.getBalance());
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        
        if (amount <= 0) {
            throw new RuntimeException("Deposit amount must be greater than zero");
        }
        
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        
        if (amount <= 0) {
            throw new RuntimeException("Withdrawal amount must be greater than zero");
        }
        
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        
        account.setBalance(account.getBalance() - amount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        accountRepository.deleteById(id);
    }

    private void validateAccountBalance(double balance) {
        if (balance < 0) {
            throw new RuntimeException("Initial balance cannot be negative");
        }
    }
}
