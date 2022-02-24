package com.sg.testkata;

import enums.OperationType;
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
    @Test
    public void should_deposit_money() {
        //arrange
        Long accountId = 1L;
        Account account = transactionService.findAccount(accountId);
        Double amount = 200D;
        Double expectedBalance = 1200D;

        //act
        transactionService.operation(OperationType.DEPOSIT, amount, accountId);

        //assert
        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_throw_exception_when_deposit_amount_is_less_than_minimum() {
        //arrange
        Long accountId = 1L;
        Double minimumDeposit = 0D;

        //assert
        Exception exception = assertThrows(AccountOperationsException.class, () -> transactionService.operation(OperationType.DEPOSIT, minimumDeposit, accountId));
    }

    @Test
    public void should_withdraw_money() {
        //arrange
        Long accountId = 4L;
        Account account = transactionService.findAccount(accountId);
        Double amount = 500D;
        Double expectedBalance = 3500D;

        //act
        transactionService.operation(OperationType.WITHDRAW, amount, accountId);

        //assert
        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }
    @Test
    public void should_throw_exception_when_withdraw_amount_is_bigger_than_balance() {
        //arrange
        Long accountId = 2L;
        Double amount = 3000D;

        //assert
        Exception exception = assertThrows(AccountOperationsException.class, () -> transactionService.operation(OperationType.WITHDRAW, amount, accountId));
    }
}

