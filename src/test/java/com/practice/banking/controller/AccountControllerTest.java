package com.practice.banking.controller;

import com.practice.banking.dto.AccountDto;
import com.practice.banking.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @Mock
    private AccountServiceImpl accountService;

    @InjectMocks
    private AccountController accountController;

    private static AccountDto newAccount;

    @BeforeAll
    public static void init() {
        newAccount = new AccountDto(1L, "John Doe", 1000.0);
    }

    @Test
    void addAccountTest() {
        Mockito.when(accountService.createAccount(newAccount)).thenReturn(newAccount);
        ResponseEntity<AccountDto> response = accountController.addAccount(newAccount);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getId(), newAccount.getId());
        Assertions.assertEquals(response.getBody().getAccountHolderName(), newAccount.getAccountHolderName());
        Assertions.assertEquals(response.getBody().getBalance(), newAccount.getBalance());
    }
}
