package service;

import enums.OperationType;
import exception.AccountOperationsException;
import model.Account;
import model.Client;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static exception.AccountOperationsException.DEPOSIT_LESS_THAN_MINIMUM_DEPOSIT;
import static exception.AccountOperationsException.ACCOUNT_NOT_FOUND;

import static exception.AccountOperationsException.INSUFFICIENT_CREDIT;

@Service
public class TransactionService {

    static final Double MIN_DEPOSIT = 0D;
    public static List<Account> accounts = Arrays.asList(new Account(1L, 1000D),
                                                         new Account(2L, 2000D),
                                                         new Account(3L, 3000D));
    public static Account client1Account = accounts.get(0);
    public static Account client2Account = accounts.get(1);
    public static Account client3Account = accounts.get(2);
    public static List<Client> clients =  Arrays.asList(new Client(1L, "client", client1Account),
                                                        new Client(2L, "client2", client2Account),
                                                        new Client(3L, "client3", client3Account));

    /**
     * recherche d'un compte bancaire d'un client à partir de son id
     * @param accountId : id du compte
     * @return compte client
     */
    public Account findAccount(Long accountId) {
        return accounts.stream()
                .filter(account -> account.getId().equals(accountId))
                .findAny()
                .orElseThrow(() -> new AccountOperationsException(ACCOUNT_NOT_FOUND));
    }

    /**
     * méthode permettant le depôt dans son compte bancaire
     * @param operationType : type de l'opération (dépôt)
     * @param amount : somme à déposer
     * @param acountId : id du compte bancaire
     * @return solde du compte mis à jour
     */
    public Account deposit(OperationType operationType, Double amount, Long acountId) {
        Account account = findAccount(acountId);
        if (operationType == OperationType.DEPOSIT) {
            if (amount > MIN_DEPOSIT) {
                account.setBalance(account.getBalance() + amount);
                return account;
            } else {
                throw new AccountOperationsException(DEPOSIT_LESS_THAN_MINIMUM_DEPOSIT);
            }
        }
        return account;
    }

    /**
     * méthode permettant le retrait dans son compte bancaire
     * @param operationType type de l'opération (retrait)
     * @param amount : somme à déposer
     * @param acountId : id du compte bancaire
     * @return solde du compte mis à jour
     */
    public Account withdraw(OperationType operationType, Double amount, Long acountId) {
        Account account = findAccount(acountId);
        if(operationType == OperationType.WITHDRAW )
        {
            if (account.getBalance() > amount) {
                account.setBalance(account.getBalance() - amount);
                return account;
            }
            else {
                throw new AccountOperationsException(INSUFFICIENT_CREDIT);
            }
        }

        return account;
    }



}
