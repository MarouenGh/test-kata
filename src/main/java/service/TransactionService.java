package service;

import enums.OperationType;
import exception.AccountOperationsException;
import model.Account;
import org.springframework.stereotype.Service;
import repository.AccountRepository;

import static exception.AccountOperationsException.DEPOSIT_LESS_THAN_MINIMUM_DEPOSIT;
import static exception.AccountOperationsException.INSUFFICIENT_CREDIT;

@Service
public class TransactionService {

    AccountRepository accountRepository = new AccountRepository();
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
     * @param acountId : id du compte bancaire
     * @return solde du compte mis à jour
     */
    public Account operation(OperationType operationName, Double amount, Long acountId) {
        Account account = findAccount(acountId);
        switch(operationName) {
            case WITHDRAW:
                if (account.getBalance() > amount) {
                    account.setBalance(account.getBalance() - amount);
                    return account;

                } else {
                    throw new AccountOperationsException(INSUFFICIENT_CREDIT);
                }
            case DEPOSIT:
                if (amount > MIN_DEPOSIT) {
                    account.setBalance(account.getBalance() + amount);
                    return account;
                } else {
                    throw new AccountOperationsException(DEPOSIT_LESS_THAN_MINIMUM_DEPOSIT);
                }
        }
        return account;
    }



}
