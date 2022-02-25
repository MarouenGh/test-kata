package repository;

import exception.AccountOperationsException;
import model.Account;
import model.Client;

import java.util.Arrays;
import java.util.List;

import static exception.AccountOperationsException.ACCOUNT_NOT_FOUND;

public class AccountRepository {
    public static List<Client> clients = ClientRepository.clients;
    public static Client client1 = clients.get(0);
    public static Client client2 = clients.get(1);
    public static Client client3 = clients.get(2);
    public static Client client4 = clients.get(3);
    public static List<Account> accounts = Arrays.asList(new Account(1L, 1000D,client1),
            new Account(2L, 2000D,client2),
            new Account(3L, 3000D,client3),
            new Account(4L, 4000D,client4));

    public AccountRepository() {}

    public Account findAccount(Long accountId) {
        return accounts.stream()
                .filter(account -> account.getId().equals(accountId))
                .findAny()
                .orElseThrow(() -> new AccountOperationsException(ACCOUNT_NOT_FOUND));
    }
}
