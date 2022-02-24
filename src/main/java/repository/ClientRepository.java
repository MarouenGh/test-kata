package repository;

import model.Account;
import model.Client;

import java.util.Arrays;
import java.util.List;


public class ClientRepository  {
    public static List<Account> accounts = AccountRepository.accounts;
    public static Account client1Account = accounts.get(0);
    public static Account client2Account = accounts.get(1);
    public static Account client3Account = accounts.get(2);
    public static List<Client> clients =  Arrays.asList(new Client(1L, "client", client1Account),
            new Client(2L, "client2", client2Account),
            new Client(3L, "client3", client3Account));

    public ClientRepository() {}

}