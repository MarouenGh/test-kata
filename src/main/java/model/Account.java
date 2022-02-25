package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Account {

    @Id
    private Long id;

    private Double balance;

    @OneToOne(fetch = FetchType.LAZY)
    private Client client;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(balance, account.balance) && Objects.equals(client, account.client);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", client=" + client +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, client);
    }

    public Account(Long id, Double balance, Client client) {
        this.id = id;
        this.balance = balance;
        this.client = client;
    }

    public Account() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Client getClient() { return client; }

    public void setClient(Client client) { this.client = client; }

}
