package repository;

import model.Client;

import java.util.Arrays;
import java.util.List;


public class ClientRepository  {
    public static List<Client> clients =  Arrays.asList(new Client(1L, "client1"),
            new Client(2L, "client2"),
            new Client(3L, "client3"),
            new Client(4L, "client4"));

    public ClientRepository() {}

}