package repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import enums.OperationType;
import model.Account;
import model.Transaction;

public class TransactionRepository {
    public static List<Transaction> transactions = new ArrayList<>();

    public void save(Account account, OperationType operation, Double amount) {
        Transaction transaction = new Transaction(LocalDateTime.now().withNano(0), operation, amount, account);
        transactions.add(transaction);
    }
    public List<Transaction> findTransactionByAccountId(Long accountId) {
        return transactions.stream()
                .filter(transaction -> transaction.getAccount().getId().equals(accountId))
                .collect(Collectors.toList());
    }
}
