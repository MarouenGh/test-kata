package com.sg.testkata;

import enums.OperationType;
import exception.AccountOperationsException;
import model.Account;
import model.Client;
import model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import service.TransactionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        Client expectedAccountClient = new Client(1L,"Client1");
        Account expectedAccount = new Account(accountId, balance, expectedAccountClient);

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
        Long accountId = 3L;
        Account account = transactionService.findAccount(accountId);
        Double amount = 200D;
        Double expectedBalance = 3200D;

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
    @Test
    public void should_search_for_transactions() {

        //arrange
        Long accountId = 1L;
        Account expectedTransactionsAccount = transactionService.findAccount(accountId);
        Double amountToDeposit = 1000D;
        Double amountToWithdraw = 150D;
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(new Transaction(LocalDateTime.now().withNano(0),OperationType.DEPOSIT,amountToDeposit,expectedTransactionsAccount));
        expectedTransactions.add(new Transaction(LocalDateTime.now().withNano(0),OperationType.WITHDRAW,amountToWithdraw, expectedTransactionsAccount));


        //act
        transactionService.operation(OperationType.DEPOSIT, amountToDeposit, accountId);
        transactionService.operation(OperationType.WITHDRAW, amountToWithdraw, accountId);
        List<Transaction> transactions = transactionService.getAccountTransactionList(accountId);

        //assert
        assertThat(transactions).isEqualTo(expectedTransactions);
    }
}

