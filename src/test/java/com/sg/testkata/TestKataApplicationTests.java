package com.sg.testkata;

import exception.AccountOperationsException;
import model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import service.TransactionService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertThrows;

@SpringBootTest
class TestKataApplicationTests {

    private TransactionService transactionService = new TransactionService();
    @Test
    public void should_find_account() {
        //arrange
        Long accountId = 1L;
        Double balance = 1000D;
        Account expectedAccount = new Account(accountId, balance);

        //act
        Account actualAccount = transactionService.findAccount(accountId);

        //assert
        assertThat(actualAccount.getId()).isEqualTo(expectedAccount.getId());
    }
    @Test
    public void should_throw_exception_when_account_not_found() {
        //arrange
        Long accountId = 10L;

        //assert
        assertThrows(AccountOperationsException.class, () -> transactionService.findAccount(accountId));
    }


}

