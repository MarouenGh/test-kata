package service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import model.Account;
import model.Client;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    static final Double MIN_DEPOSIT = 0.01;
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
    public Optional<Account> findAccount(Long accountId) {
        return accounts.stream()
                .filter(account -> account.getId().equals(accountId))
                .findAny();
    }




}
