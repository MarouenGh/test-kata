package service;

import enums.OperationType;
import exception.AccountOperationsException;
import model.Account;
import model.Transaction;
import org.springframework.stereotype.Service;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.util.List;

import static exception.AccountOperationsException.DEPOSIT_LESS_THAN_MINIMUM_DEPOSIT;
import static exception.AccountOperationsException.INSUFFICIENT_CREDIT;

@Service
public class TransactionService {

    AccountRepository accountRepository = new AccountRepository();
    TransactionRepository transactionRepository= new TransactionRepository();
    static final Double MIN_DEPOSIT = 0D;

    /**
     * recherche d'un compte bancaire d'un client à partir de son id
     * @param accountId : id du compte
     * @return compte client
     */
    public Account findAccount(Long accountId) {
        return accountRepository.findAccount(accountId);
    }

    /**
     * méthode permettant le depôt/retrait dans son compte bancaire
     * @param operationName : type de l'opération (dépôt/retrait)
     * @param amount : montant du transaction
     * @param accountId : id du compte bancaire
     * @return solde du compte mis à jour
     */
    public Account operation(OperationType operationName, Double amount, Long accountId) {
        Account account = findAccount(accountId);
        switch (operationName) {
            case WITHDRAW:
                if (account.getBalance() > amount) {
                    account.setBalance(account.getBalance() - amount);
                    transactionRepository.save(account, operationName, amount);
                    return account;
                } else {
                    throw new AccountOperationsException(INSUFFICIENT_CREDIT);
                }
            case DEPOSIT:
                if (amount > MIN_DEPOSIT) {
                    account.setBalance(account.getBalance() + amount);
                    transactionRepository.save(account, operationName, amount);
                    return account;
                } else {
                    throw new AccountOperationsException(DEPOSIT_LESS_THAN_MINIMUM_DEPOSIT);
                }
        }
        return account;
    }

    /**
     * Recherche des transactions effectués par un compte
     * @param accountId : id du compte bancaire
     * @return : la liste des transactions du compte associé
     */
    public List<Transaction> getAccountTransactionList(Long accountId) {
            List<Transaction> transactionList = null;
            List<Transaction> accountTransactionList = transactionRepository.findTransactionByAccountId(accountId);
            if (accountTransactionList.size() > 0) {
                transactionList = transactionRepository.findTransactionByAccountId(accountId);
            }
            return transactionList;
        }

}
