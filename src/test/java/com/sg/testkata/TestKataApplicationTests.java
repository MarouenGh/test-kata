package com.sg.testkata;

import model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import service.TransactionService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        Optional<Account> actualAccount = transactionService.findAccount(accountId);

        //assert
        assertThat(actualAccount.get().getId()).isEqualTo(expectedAccount.getId());
    }

}

